package com.zzzj.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Zzzj
 * @create 2021-12-12 17:49
 */
public class GuaHao {

    private int times = 0;

    private Map<String, Integer> previousRecord = new HashMap<>();

    private Set<String> notifyRecord = new HashSet<>();

    private final String url;

    private String doctorName;

    private static final File mp3File = new File("C:\\Users\\zzzj\\Downloads\\jj.mp3"); // 替换为实际的文件路径

    private static final Desktop desktop = Desktop.getDesktop();

    public GuaHao(String url) {
        this.url = url;
    }

    private void ask() throws Exception {

        HttpRequest httpRequest = HttpUtil.createGet(this.url);

        log("Before request");

        HttpResponse response = null;
        try {
            httpRequest.setConnectionTimeout(5000);
            httpRequest.setReadTimeout(5000);
            response = httpRequest.execute();
        } catch (Exception e) {
            log("Request error : " + e);
            return;
        }

        log("After request");

        String body = response.body();

        JSONObject jsonObj = JSONUtil.parseObj(body);

        Map<String, Integer> record = new HashMap<>(16);

        List<LocalDate> expectDays = Arrays.asList(
                LocalDate.of(2023, 12, 2)
        );

        // schedule_id
        Optional.ofNullable(jsonObj.getJSONObject("data"))
                .ifPresent(jsonObject -> jsonObject.getJSONArray("sch_list")
                        .stream()
                        .map(JSONObject::new)
                        .map(schList -> schList.getJSONObject("sch"))
                        .filter(Objects::nonNull)
                        .flatMap(schList -> {
                            JSONObject am = Optional.ofNullable(schList.getJSONArray("am"))
                                    .map(CollUtil::getFirst)
                                    .map(JSONObject::new)
                                    .orElse(null);

                            JSONObject pm = Optional.ofNullable(schList.getJSONArray("pm"))
                                    .map(CollUtil::getFirst)
                                    .map(JSONObject::new)
                                    .orElse(null);

                            return ListUtil.toList(am, pm).stream().filter(Objects::nonNull);
                        })
                        .filter(amOrPm -> amOrPm.getInt("left_num", 0) > 0)
                        .forEach(amOrPm -> {

                            this.doctorName = amOrPm.getStr("doctor_name");

                            String date = amOrPm.getStr("to_date");

                            String timeType = amOrPm.getStr("time_type");

                            Integer leftNum = amOrPm.getInt("left_num");

                            Optional.ofNullable(date)
                                    .map(s -> s.concat(Optional.ofNullable(timeType).orElse(StrUtil.EMPTY)))
                                    .filter(StrUtil::isNotBlank)
                                    .filter(s -> leftNum != null)
                                    .ifPresent(s -> record.put(s, leftNum));

                            LocalDate reserveDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

                            if (expectDays.contains(reserveDate)) {
                                if (!notifyRecord.contains(date + timeType)) {
                                    try {
                                        notice(date, timeType);
                                        notifyRecord.add(date + timeType);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        })
                );

        previousRecord = record;

        times++;

        if (times % 10 == 0)
            log(record.toString());

        log("times = " + times);
        log("=====================================================");

        response.close();
    }

    private void log(String info) {
        if (doctorName == null)
            System.out.printf(" ( %s ) : %s %n", LocalDateTime.now().format(DateTimeFormatter.ISO_TIME), info);
        else
            System.out.printf("%s ( %s ) -> : %s %n", doctorName, LocalDateTime.now().format(DateTimeFormatter.ISO_TIME), info);
    }

    public void notice(String date, String timeType) throws Exception {

        log(String.format("Got : %s --- %s ", date, timeType));

        if (mp3File.exists() && desktop.isSupported(Desktop.Action.OPEN)) {
            desktop.open(mp3File);
            return;
        }

        log("Before displayTray");
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("Zzzzj");
        tray.add(trayIcon);

        trayIcon.displayMessage("有号了!", "有号了!!!!!!", TrayIcon.MessageType.INFO);

        log("After displayTray");
    }

    public static void main(String[] args) throws IOException, AWTException {

        List<String> urls = Arrays.asList(
                "https://wechatgate.91160.com/guahao/v1-1/sch/union/doctor?city_id=5&cid=23&sc_distinct_id=258327649&trace_id=bf084dc7-4fad-4113-96a6-2b8820232750-1701157382723&user_key=756080f634215a54fd8f9d264dba983dhPFoJlRW20231228154122&account_user_id=50045362&dep_id=372&doctor_id=16698&all_point=1&page=1&select_date=&user_id=258327649&user_key=756080f634215a54fd8f9d264dba983dhPFoJlRW20231228154122&unit_id=131"
        );

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);

        for (String url : urls) {

            GuaHao reserve = new GuaHao(url);

            pool.scheduleWithFixedDelay(() -> {
                try {
                    reserve.ask();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, 0, 30, TimeUnit.SECONDS);
        }
    }

}
