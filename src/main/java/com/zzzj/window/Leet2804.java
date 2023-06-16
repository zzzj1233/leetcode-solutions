package com.zzzj.window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-06-13 11:08
 */
public class Leet2804 {

    public static void main(String[] args) {

        System.out.println(beautifulBouquet(new int[]{5, 3, 3, 3}, 2));

        System.out.println(beautifulBouquet(new int[]{1, 2, 3, 2}, 1));

    }

    public static int beautifulBouquet(int[] flowers, int cnt) {

        final int MOD = 1000000007;

        int N = flowers.length;

        int left = 0;

        int right = 0;

        Map<Integer, Integer> map = new HashMap<>();

        long ans = 0;

        while (right < N) {

            int f = flowers[right];

            Integer old = map.getOrDefault(f, 0);

            if (old + 1 > cnt) {

                while (map.get(f) == cnt) {
                    int f2 = flowers[left];
                    map.put(f2, map.get(f2) - 1);
                    left++;
                }

            }

            map.put(f, map.getOrDefault(f, 0) + 1);

            ans += right - left + 1;

            right++;
        }

        return (int) (ans % MOD);
    }

}
