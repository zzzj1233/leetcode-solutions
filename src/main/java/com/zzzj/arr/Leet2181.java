package com.zzzj.arr;

import java.util.Arrays;

public class Leet2181 {

    public static long minimumTime(int[] time, int totalTrips) {

        long low = 1;
        long high = (long) Arrays.stream(time).max().getAsInt() * totalTrips;

        while (low < high) {
            long mid = low + ((high - low) >> 1);
            if (check(time, totalTrips, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return high;
    }

    public static boolean check(int[] time, int totalTrips, long expect) {
        int cur = 0;

        for (int it : time) {
            cur += expect / it;
        }

        return cur >= totalTrips;
    }
}
