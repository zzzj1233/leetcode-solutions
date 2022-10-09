package com.zzzj.leet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Leet1429 {


    private static class FirstUnique {

        Map<Integer, Integer> counter = new HashMap<>();

        LinkedList<Integer> min = new LinkedList<>();

        public FirstUnique(int[] nums) {
            for (int num : nums) {
                add(num);
            }
        }

        public int showFirstUnique() {
            while (!min.isEmpty()) {
                Integer first = min.peekFirst();
                if (counter.get(first) > 1) {
                    min.removeFirst();
                    continue;
                }
                return first;
            }
            return -1;
        }

        public void add(int value) {
            Integer count = counter.get(value);

            if (count != null) {
                counter.put(value, 2);
            } else {
                counter.put(value, 1);
                min.add(value);
            }
        }

    }

}
