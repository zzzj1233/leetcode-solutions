package com.zzzj.design;

import javax.swing.border.EmptyBorder;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2023-05-06 18:01
 */
public class Leet729 {

    private static class MyCalendar {

        // 包左不包右
        private final TreeMap<Integer, Integer> startMap = new TreeMap<>();


        public MyCalendar() {

        }

        public boolean book(int start, int end) {
            Map.Entry<Integer, Integer> startFloor = startMap.floorEntry(start);

            if (startFloor != null && startFloor.getValue() > start) {
                return false;
            }

            Map.Entry<Integer, Integer> startCeiling = startMap.ceilingEntry(start);

            if (startCeiling != null && startCeiling.getKey() < end) {
                return false;
            }

            startMap.put(start, end);

            return true;
        }

    }

}
