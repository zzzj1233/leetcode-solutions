package com.zzzj.leet;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Zzzj
 * @create 2022-07-31 19:55
 */
public class Leet729 {


    public static void main(String[] args) {
    }

    private static class MyCalendar {

        TreeMap<Integer, Integer> startMap;

        public MyCalendar() {
            startMap = new TreeMap<>();
        }

        // 包左不包右
        public boolean book(int start, int end) {
            Map.Entry<Integer, Integer> startFloor = startMap.floorEntry(start);

            Map.Entry<Integer, Integer> startCeil = startMap.ceilingEntry(start);

            Map.Entry<Integer, Integer> endFloor = startMap.floorEntry(end - 1);

            if (startMap.containsKey(start)) {
                return false;
            }

            if (startFloor != null && start < startFloor.getValue()) {
                return false;
            }

            if (startCeil != null && startCeil.getKey() < end) {
                return false;
            }

            if (endFloor != null && endFloor.getValue() > end) {
                return false;
            }

            startMap.put(start, end);

            return true;
        }


    }

}
