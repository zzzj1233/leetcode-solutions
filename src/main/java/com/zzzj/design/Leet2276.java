package com.zzzj.design;


import java.util.Map;
import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2023-09-19 17:07
 */
public class Leet2276 {


    private static class CountIntervals {

        private final TreeMap<Integer, Integer> startMap;

        private final TreeMap<Integer, Integer> endMap;

        private int count;

        public CountIntervals() {
            startMap = new TreeMap<>();
            endMap = new TreeMap<>();
        }

        public void add(int left, int right) {

            int cnt = right - left + 1;

            Map.Entry<Integer, Integer> end = endMap.floorEntry(left);

            Map.Entry<Integer, Integer> start = startMap.ceilingEntry(right);

            // 合并区间
            if (start != null && end != null) {
                cnt -= end.getKey() - left + 1;
                cnt -= right - start.getKey() + 1;

                startMap.remove(start.getKey());
                endMap.remove(end.getKey());

                startMap.put(end.getKey(), start.getValue());
                endMap.put(start.getValue(), end.getKey());
            } else if (start != null) {
                if (start.getKey() <= left)
                    return;

                cnt -= right - start.getKey() + 1;

                int $end = Math.max(start.getValue(), right);

                startMap.remove(start.getKey());
                startMap.put(left, $end);
                endMap.put($end, left);
            } else if (end != null) {
                cnt -= end.getKey() - left + 1;

            }

            count += cnt;

        }

        public int count() {
            return -1;
        }

    }

}
