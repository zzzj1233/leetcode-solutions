package com.zzzj.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2021-12-09 12:09
 */
public class LunchChooser {

    private static final String[] CANDIDATE = {"鲍汁饭", "蒸菜", "酸菜鱼", "嘉旺", "猪脚饭"};

    private static final double[] WEIGHT = {1.25, 1.25, 1, 1, 1};

    private static final String WEBHOOK_URL = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=9faf6091-6e80-4baf-80e7-eb1507daaf28";

    private static final int TIMES = 100;

    public static void main(String[] args) {
        // [38, 40, 17, 23, 21]
        double[] choose = choose();

        // counter
        Map<String, Double> counter = new HashMap<>(choose.length);

        for (int i = 0; i < choose.length; i++) {
            counter.put(CANDIDATE[i], choose[i]);
        }

        List<Map.Entry<String, Double>> collect = counter.entrySet()
                .stream()
                .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
                .collect(Collectors.toList());

        String json = JSONUtil.toJsonStr(MapUtil.builder().put("msgtype", "text").put("text", MapUtil.builder().put("content", collect.toString()).build()).build());

        HttpUtil.post(WEBHOOK_URL, json);
    }


    public static double[] choose() {
        Random random = new Random();

        // 选100次,选中1次得1分
        // score * weight

        int max = CANDIDATE.length;
        int min = 0;

        int n = WEIGHT.length;

        double[] scores = new double[n];

        for (int i = 0; i < TIMES; i++) {
            int index = Math.abs(random.nextInt()) % n;
            double score = WEIGHT[index];
            scores[index] += score;
        }

        return scores;
    }

}
