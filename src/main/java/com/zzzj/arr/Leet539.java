package com.zzzj.arr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Leet539 {

    public static void main(String[] args) {
        System.out.println(findMinDifference(Arrays.asList("23:59", "00:00")));
    }

    public static int findMinDifference(List<String> timePoints) {
        List<Integer> list = new ArrayList<>(timePoints.size());

        for (String timePoint : timePoints) {
            int[] ints = toMinute(timePoint);
            list.add(ints[0] * 60 + ints[1]);
        }

        list.sort(Comparator.comparingInt(o -> o));

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < list.size(); i++) {
            ans = Math.min(list.get(i) - list.get(i - 1), ans);
        }

        if (list.size() > 1) {
            ans = Math.min(ans, 1400 + list.get(0) - list.get(list.size() - 1));
        }

        return ans;
    }

    public static int[] toMinute(String time) {
        String[] split = time.split(":");
        String hourStr = split[0];
        String minuteStr = split[1];

        int hour = Character.digit(hourStr.charAt(0), 10) * 10 + Character.digit(hourStr.charAt(1), 10);
        int minute = Character.digit(minuteStr.charAt(0), 10) * 10 + Character.digit(minuteStr.charAt(1), 10);

        return new int[]{hour, minute};
    }

}
