package com.zzzj.design;

import java.util.HashMap;
import java.util.Map;

public class Leet170 {


    private static class TwoSum {

        private Map<Integer, Integer> map = new HashMap<>();

        public TwoSum() {

        }

        public void add(int number) {
            map.put(number, map.getOrDefault(number, 0) + 1);
        }

        public boolean find(int value) {

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                Integer key = entry.getKey();
                int another = value - key;

                if (another == key) {
                    if (entry.getValue() > 1)
                        return true;
                } else if (map.containsKey(another))
                    return true;
            }

            return false;
        }
    }

}
