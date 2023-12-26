package com.zzzj.design;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2023-11-14 11:50
 */
public class Leet352 {

    public static void main(String[] args) {

        SummaryRanges ranges = new SummaryRanges();

        ranges.addNum(1);
        ranges.addNum(3);
        ranges.addNum(2);

        System.out.println("ranges.getIntervals() = " + Arrays.deepToString(ranges.getIntervals()));

        ranges.addNum(2);

    }

    private static class SummaryRanges {

        private TreeMap<Integer, Integer> low = new TreeMap<>();

        private TreeMap<Integer, Integer> high = new TreeMap<>();

        public SummaryRanges() {

        }

        public void addNum(int value) {

            Map.Entry<Integer, Integer> ceiling = low.ceilingEntry(value);

            if (ceiling != null && ceiling.getValue() <= value)
                return;

            Map.Entry<Integer, Integer> floor = high.floorEntry(value);

            if (floor != null && floor.getValue() >= value)
                return;

            Integer lowStart = low.get(value - 1);

            Integer highEnd = high.get(value + 1);

            // low  : 2-1
            // high : 4-5
            // value: 3
            // low : 5-1
            // high: 1-5
            if (lowStart != null && highEnd != null) {

                low.remove(value - 1);

                high.remove(value + 1);

                low.put(highEnd, lowStart);

                high.put(lowStart, highEnd);

            } else if (lowStart != null) {
                // low  : 2-1

                // value: 3

                // low: 3-1

                // high: 1-3
                low.remove(value - 1);
                low.put(value, lowStart);

                high.put(lowStart, value);
            } else if (highEnd != null) {
                // high : 4-5
                // low: 5-4
                // value: 3

                // after:
                // low:  5-3
                // high: 3-5
                high.remove(value + 1);
                high.put(value, highEnd);
                low.put(highEnd, value);
            } else {
                high.put(value, value);
                low.put(value, value);
            }


        }

        public int[][] getIntervals() {
            return high.entrySet().stream().map(entry -> new int[]{entry.getKey(), entry.getValue()}).toArray(int[][]::new);
        }

    }

}
