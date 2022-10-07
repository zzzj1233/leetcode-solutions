package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author zzzj
 * @create 2022-10-07 21:35
 */
public class Leet1497 {

    public static void main(String[] args) {
        System.out.println(canArrange(new int[]{1, 2, 3, 4, 5, 10, 6, 7, 8, 9}, 5));
    }

    // [5,5,1,2,3,4]
    // 10
    public static boolean canArrange(int[] arr, int k) {

        Map<Integer, Integer> map = new HashMap<>(arr.length);

        int sum = 0;

        for (int it : arr) {
            map.put(it % k, map.getOrDefault(it % k, 0) + 1);
            sum += it;
        }

        if (sum % k != 0) {
            return false;
        }

        for (Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer count = entry.getValue();
            if (key == 0 || key == (k - key)) {
                if (map.get(key) % 2 != 0) {
                    return false;
                }
            } else {
                if (!map.getOrDefault(k - key, 0).equals(count)) {
                    return false;
                }
            }
        }

        return true;
    }

}
