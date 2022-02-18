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
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Zzzj
 * @create 2021-12-12 17:49
 */
public class GuaHao {

    private static int times = 0;

    private static Map<String, Integer> previousRecord;

    private static Set<String> notifyRecord;

    private static void ask() throws Exception {
        String url = "https://wechatgate.91160.com/guahao/v1/sch/union/doctor?cid=23&user_key=2530ec03022e84f6522f202dbd48a808HLzlfXdT20211213214337&dep_id=404&doctor_id=1308&all_point=0&page=1&select_date=&user_id=258327649&user_key=2530ec03022e84f6522f202dbd48a808HLzlfXdT20211213214337&unit_id=100";

        HttpRequest httpRequest = HttpUtil.createGet(url);

        HttpResponse response = httpRequest.execute();

        String body = response.body();

        JSONObject jsonObj = JSONUtil.parseObj(body);

        Map<String, Integer> record = new HashMap<>(16);

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

                            String date = amOrPm.getStr("to_date");

                            String timeType = amOrPm.getStr("time_type");

                            Integer leftNum = amOrPm.getInt("left_num");

                            System.out.printf("%s (%s) : %d \n", date, timeType, leftNum);

                            Optional.ofNullable(date)
                                    .map(s -> s.concat(Optional.ofNullable(timeType).orElse(StrUtil.EMPTY)))
                                    .filter(StrUtil::isNotBlank)
                                    .filter(s -> leftNum != null)
                                    .ifPresent(s -> record.put(s, leftNum));

                            LocalDate reserveDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

                            if (reserveDate.equals(LocalDate.now()) || reserveDate.equals(LocalDate.now().plusDays(1L))) {
                                if (!notifyRecord.contains(date + timeType)) {
                                    try {
                                        displayTray();
                                        notifyRecord.add(date + timeType);
                                    } catch (AWTException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        })
                );

        if (!previousRecord.equals(record)) {
            System.out.println(body);
        }

        previousRecord = record;

        times++;

        System.out.println("times = " + times);
        System.out.println("=====================================================");

        response.close();
    }

    public static void displayTray() throws AWTException {
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
    }

    public static void main(String[] args) throws IOException, AWTException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

        previousRecord = new HashMap<>();

        notifyRecord = new HashSet<>();

        pool.scheduleWithFixedDelay(() -> {
            try {
                ask();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 30, TimeUnit.SECONDS);
    }

}
