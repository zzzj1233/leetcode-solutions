package com.zzzj.other;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-02-24 11:12
 */
public class ProductionErrorChecker {

    public static final Log log = Log.get(ProductionErrorChecker.class);

    public static final String START = DateUtil.parse("2022-02-18").getTime() + "000000";

    public static final String END = DateUtil.parse("2022-02-19").getTime() + "000000";

    public static final String URL = "http://43.132.181.171:3300/api/datasources/proxy/1/loki/api/v1/query_range";

    public static final String COOKIE_VALUE = "td_cookie=1738395107; grafana_session=22bdf0e891c0bbcdd4420e9e0c9d6d97";

    public static void main(String[] args) {
        Set<String> set = findTraceIdByErrorCode();

        ExecutorService executorService = Executors.newFixedThreadPool(32);

        final List<Future<String>> futures = set
                .stream()
                .map(traceId -> executorService.submit(() -> requestByTraceId(traceId)))
                .collect(Collectors.toList());

        List<String> traceIds = futures.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).filter(Objects::nonNull)
                .collect(Collectors.toList());

        System.out.println(traceIds);

        executorService.shutdownNow();
    }

    public static Set<String> findTraceIdByErrorCode() {
        String query = "{filename=~\"/data/logs/goldhorse-.*/container.log\"}  |~ \"run controller validator failed.*code=(40021|5)\"";

        HttpRequest normalRequest = createNormalRequest(query);

        try {
            return processResult(normalRequest, result -> {
                // 找到所有的traceId
                Set<String> set = new HashSet<>();

                for (int i = 0; i < result.size(); i++) {
                    JSONObject jsonObject = (JSONObject) result.get(i);
                    JSONArray values = jsonObject.getJSONArray("values");

                    for (int j = 0; j < values.size(); j++) {
                        JSONArray jsonArray = values.getJSONArray(j);
                        String s = jsonArray.getStr(jsonArray.size() - 1);
                        // 找到traceId
                        int infoIndex = s.indexOf("INFO");
                        // 从infoIndex往左找两个中括号
                        boolean start = false;
                        StringBuilder builder = new StringBuilder();
                        while (infoIndex >= 0) {
                            if (s.charAt(infoIndex) == ']') {
                                start = true;
                            } else if (start) {
                                if (s.charAt(infoIndex) == '[') {
                                    set.add(builder.reverse().toString().trim());
                                    break;
                                } else {
                                    builder.append(s.charAt(infoIndex));
                                }
                            }
                            infoIndex--;
                        }
                    }
                }

                return set;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptySet();
    }

    public static String requestByTraceId(String tradeId) {
        String query = String.format("{filename=~\"/data/logs/goldhorse-.*/container.log\"}  |~ \"%s\"", tradeId);

        HttpRequest normalRequest = createNormalRequest(query);

        try {
            return processResult(normalRequest, result -> {
                // 获取deviceType
                JSONObject lastResult = (JSONObject) result.get(result.size() - 1);

                JSONArray values = lastResult.getJSONArray("values");

                final String s = values.getStr(values.size() - 1);

                int index = s.indexOf("deviceType");

                if (index == -1) {
                    log.error("requestByTraceId response deviceType can't find , tradeId = {} , response = {} ", tradeId, result);
                    return null;
                }

                int end = s.indexOf("\\n", index);

                String deviceType = s.substring(index + "deviceType: ".length(), end).trim();

                log.info("tradeId = {} , deviceType = {} ", tradeId, deviceType);

                if (!"WEB".equalsIgnoreCase(deviceType)) {
                    return tradeId;
                }

                return null;
            });
        } catch (Exception e) {
            log.error("requestByTraceId request error , tradeId = {} ", e, tradeId);
        }

        return null;
    }

    public static <T> T processResult(HttpRequest request, Function<JSONArray, T> process) throws Exception {

        try (HttpResponse response = request.execute()) {

            String body = response.body();

            JSONObject jsonObject = JSONUtil.parseObj(body).getJSONObject("data");

            JSONArray result = jsonObject.getJSONArray("result");

            return process.apply(result);
        }

    }


    public static HttpRequest createNormalRequest(String query) {
        HttpRequest httpRequest = HttpUtil.createGet(URL);
        httpRequest.form("direction", "BACKWARD");
        httpRequest.form("limit", "1000");
        httpRequest.form("start", START);
        httpRequest.form("end", END);
        httpRequest.form("step", 120);
        httpRequest.form("query", query);
        httpRequest.header("Cookie", COOKIE_VALUE);
        return httpRequest;
    }

}
