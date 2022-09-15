package com.zzzj.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2022-09-08 10:33
 */
public class Leet1481 {

    public static void main(String[] args) {
        // [2,1,1,3,3,3]
        // 3
        System.out.println(findLeastNumOfUniqueInts(new int[]{2, 1, 1, 3, 3, 3}, 3));
    }

    public static int findLeastNumOfUniqueInts(int[] arr, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        TreeMap<Integer, Integer> counter = new TreeMap<>();

        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer count = entry.getValue();
            counter.put(count, counter.getOrDefault(count, 0) + 1);
        }

        int ans = map.size();

        while (k > 0 && !counter.isEmpty()) {
            Map.Entry<Integer, Integer> minEntry = counter.pollFirstEntry();

            Integer count = minEntry.getKey();

            Integer length = minEntry.getValue();

            if (k < count) {
                return ans;
            }

            int next = Math.max(0, k - (count * length));

            int sub = k - next;

            ans -= sub / count;

            k = next;

        }

        return ans;
    }

}
