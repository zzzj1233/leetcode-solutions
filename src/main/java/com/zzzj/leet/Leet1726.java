package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-10-07 18:52
 */
public class Leet1726 {

    public static int tupleSameProduct(int[] nums) {
        int ans = 0;

        Map<Integer, Integer> map = new HashMap<>();

        int N = nums.length;

        for (int i = 0; i < N; i++) {

            for (int j = i + 1; j < N; j++) {

                int sum = nums[i] * nums[j];

                ans += map.getOrDefault(sum, 0);

                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }

        }

        return ans << 3;
    }

}
