package com.zzzj.window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2021-12-29 16:09
 */
public class Leet904 {

    public static void main(String[] args) {

        // 1,2,1,1,2
        System.out.println(totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));

        // 2322
        System.out.println(totalFruit(new int[]{1, 2, 3, 2, 2}));
        System.out.println(totalFruit(new int[]{0, 1, 2, 2}));
        System.out.println(totalFruit(new int[]{1, 2, 1}));

    }

    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> record = new HashMap<>();

        int l = 0;
        int r = 0;

        int ans = 0;

        while (r < fruits.length) {
            record.put(fruits[r], record.getOrDefault(fruits[r], 0) + 1);

            while (record.size() > 2) {
                Integer count = record.get(fruits[l]);
                if (count == 1) {
                    record.remove(fruits[l]);
                } else {
                    record.put(fruits[l], count - 1);
                }
                l++;
            }

            ans = Math.max(ans, r - l + 1);

            r++;
        }

        return ans;
    }

}
