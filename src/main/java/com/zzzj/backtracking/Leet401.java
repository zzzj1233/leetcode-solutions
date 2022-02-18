package com.zzzj.backtracking;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-02-14 11:43
 */
public class Leet401 {

    public static void main(String[] args) {
        // System.out.println(readBinaryWatch(1));
        // System.out.println(readBinaryWatch(2));

        final HashSet<String> except = new HashSet<>(Arrays.asList(new String[]{"1:31", "1:47", "1:55", "1:59", "2:31", "2:47", "2:55", "2:59", "3:15", "3:23", "3:27", "3:29", "3:30", "3:39", "3:43", "3:45", "3:46", "3:51", "3:53", "3:54", "3:57", "3:58", "4:31", "4:47", "4:55", "4:59", "5:15", "5:23", "5:27", "5:29", "5:30", "5:39", "5:43", "5:45", "5:46", "5:51", "5:53", "5:54", "5:57", "5:58", "6:15", "6:23", "6:27", "6:29", "6:30", "6:39", "6:43", "6:45", "6:46", "6:51", "6:53", "6:54", "6:57", "6:58", "7:07", "7:11", "7:13", "7:14", "7:19", "7:21", "7:22", "7:25", "7:26", "7:28", "7:35", "7:37", "7:38", "7:41", "7:42", "7:44", "7:49", "7:50", "7:52", "7:56", "8:31", "8:47", "8:55", "8:59", "9:15", "9:23", "9:27", "9:29", "9:30", "9:39", "9:43", "9:45", "9:46", "9:51", "9:53", "9:54", "9:57", "9:58", "10:15", "10:23", "10:27", "10:29", "10:30", "10:39", "10:43", "10:45", "10:46", "10:51", "10:53", "10:54", "10:57", "10:58", "11:07", "11:11", "11:13", "11:14", "11:19", "11:21", "11:22", "11:25", "11:26", "11:28", "11:35", "11:37", "11:38", "11:41", "11:42", "11:44", "11:49", "11:50", "11:52", "11:56"}));

        final HashSet<String> actual = new HashSet<>(Arrays.asList(new String[]{"5:60", "11:19", "11:21", "5:58", "6:27", "10:53", "6:29", "11:22", "10:54", "11:25", "10:57", "3:39", "11:26", "10:58", "5:51", "5:53", "3:30", "5:54", "6:23", "10:51", "5:57", "9:15", "8:47", "9:30", "11:28", "3:45", "3:46", "9:29", "7:07", "6:39", "11:35", "11:38", "11:37", "9:23", "6:30", "8:55", "9:27", "10:60", "8:59", "3:43", "10:27", "10:29", "1:31", "3:57", "10:30", "7:19", "3:58", "7:11", "6:43", "3:51", "7:13", "6:45", "3:53", "9:39", "3:54", "7:14", "6:46", "9:53", "9:51", "6:60", "11:07", "10:39", "7:28", "11:11", "10:43", "11:14", "10:46", "11:13", "10:45", "9:45", "3:60", "9:46", "6:51", "7:22", "9:43", "6:54", "7:21", "1:47", "6:53", "4:31", "7:26", "6:58", "7:25", "6:57", "9:60", "7:38", "5:15", "4:47", "10:15", "9:57", "9:54", "1:59", "7:35", "7:37", "9:58", "1:55", "7:50", "7:49", "2:31", "5:27", "4:59", "5:29", "10:23", "7:42", "7:41", "7:44", "5:23", "4:55", "11:42", "11:44", "3:15", "5:39", "11:49", "7:52", "5:30", "2:47", "7:56", "11:41", "3:23", "6:15", "11:56", "3:27", "3:29", "8:31", "2:59", "11:50", "5:43", "11:52", "5:45", "5:46", "2:55"}));

        actual.removeAll(except);

        System.out.println(actual);

        System.out.println(readBinaryWatch(6));
    }

    public static final int[] HOUR = {1, 2, 4, 8};

    public static final int[] MINUTE = {1, 2, 4, 8, 16, 32};

    public static List<String> readBinaryWatch(int turnedOn) {
        Set<String> ans = new HashSet<>();

        process(ans, new boolean[HOUR.length], new boolean[MINUTE.length], 0, 0, turnedOn);

        return new ArrayList<>(ans);
    }

    public static String timeToString(int hour, int minute) {
        StringBuilder builder = new StringBuilder(4);

        builder.append(hour);
        builder.append(":");

        if (minute < 10) {
            builder.append("0");
        }

        builder.append(minute);

        return builder.toString();
    }

    public static void process(Set<String> ans,
                               boolean[] usedHour,
                               boolean[] usedMinute,
                               int curHour,
                               int curMinute,
                               int turnedOn) {
        if (curHour > 11 || curMinute >= 60) {
            return;
        }

        if (turnedOn == 0) {
            ans.add(timeToString(curHour, curMinute));
            return;
        }

        for (int i = 0; i < HOUR.length; i++) {
            if (usedHour[i]) {
                continue;
            }
            usedHour[i] = true;
            process(ans, usedHour, usedMinute, curHour + HOUR[i], curMinute, turnedOn - 1);
            usedHour[i] = false;
        }

        for (int i = 0; i < MINUTE.length; i++) {
            if (usedMinute[i]) {
                continue;
            }
            usedMinute[i] = true;
            process(ans, usedHour, usedMinute, curHour, curMinute + MINUTE[i], turnedOn - 1);
            usedMinute[i] = false;
        }


    }

}
