package com.zzzj.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-09-02 18:18
 */
public class Leet1396 {

    private static class UndergroundSystem {

        Map<Integer, String[]> map = new HashMap<>();

        Map<String, int[]> average = new HashMap<>();

        public UndergroundSystem() {

        }

        public void checkIn(int id, String stationName, int t) {
            map.put(id, new String[]{stationName, String.valueOf(t)});
        }

        public void checkOut(int id, String stationName, int t) {
            String[] strings = map.get(id);

            String station = strings[0];

            int startTime = Integer.parseInt(strings[1]);

            String key = stationName + "-" + station;

            int[] ints = average.computeIfAbsent(key, k -> new int[2]);

            ints[0] += t - startTime;
            ints[1]++;
        }

        public double getAverageTime(String startStation, String endStation) {
            int[] ints = average.get(endStation + "-" + startStation);

            if (ints == null) {
                return 0;
            }

            return ((double) ints[0] / ints[1]);
        }

    }


}
