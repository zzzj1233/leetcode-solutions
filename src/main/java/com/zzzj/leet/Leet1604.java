package com.zzzj.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2023-06-13 16:28
 */
public class Leet1604 {

    public static void main(String[] args) {
        System.out.println(alertNames(
                new String[]{"a", "a", "a", "a", "a", "b", "b", "b", "b", "b", "b"},
                new String[]{"23:20", "11:09", "23:30", "23:02", "15:28", "22:57", "23:40", "03:43", "21:55", "20:38", "00:19"}
        ));
    }

    public static List<String> alertNames(String[] keyName, String[] keyTime) {

        TreeMap<String, List<int[]>> rec = new TreeMap<>();

        int N = keyName.length;

        for (int i = 0; i < N; i++) {

            List<int[]> timeRec = rec.computeIfAbsent(keyName[i], s -> new ArrayList<>());

            String[] split = keyTime[i].split(":");

            int hour = Integer.parseInt(split[0]);
            int minutes = Integer.parseInt(split[1]);

            timeRec.add(new int[]{hour, minutes});

        }

        return rec.entrySet()
                .stream()
                .filter(entry -> match(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static boolean match(List<int[]> timeRec) {

        // 先排序
        timeRec.sort((o1, o2) -> {
            int h1 = o1[0];
            int h2 = o2[0];

            int m1 = o1[1];
            int m2 = o2[1];

            if (h2 == h1) return m1 - m2;

            return h1 - h2;
        });

        // 后二分
        int N = timeRec.size();

        for (int i = 0; i < N; i++) if (can(timeRec, i)) return true;

        return false;
    }


    public static boolean can(List<int[]> timeRec, int index) {

        int[] pair = timeRec.get(index);

        int hour = pair[0] + 1;

        int minute = pair[1];

        int left = index + 1;

        int right = timeRec.size() - 1;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            int[] midPair = timeRec.get(mid);

            int mh = midPair[0];

            int mm = midPair[1];

            if (mh < hour || (mh == hour && mm <= minute)) {

                if (mid - index >= 2) return true;

                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return false;
    }

}
