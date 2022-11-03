package com.zzzj.greedy;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2022-11-01 18:26
 */
public class Leet1296 {

    public static boolean isPossibleDivide(int[] nums, int k) {
        int N = nums.length;

        if (N % k != 0) {
            return false;
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int it : nums) {
            map.put(it, map.getOrDefault(it, 0) + 1);
        }

        while (!map.isEmpty()) {
            Map.Entry<Integer, Integer> first = map.pollFirstEntry();

            Integer key = first.getKey();
            Integer c = first.getValue();

            for (int i = 1; i < k; i++) {
                Integer bigger = map.get(key + i);
                if (bigger == null || bigger < c) {
                    return false;
                }
                if (bigger - c > 0) {
                    map.put(key + i, bigger - c);
                } else {
                    map.remove(key + i);
                }
            }

        }

        return true;
    }

}
