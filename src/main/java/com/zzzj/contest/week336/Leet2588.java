package com.zzzj.contest.week336;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-08-02 16:55
 */
public class Leet2588 {

    public static void main(String[] args) {

        System.out.println(beautifulSubarrays(new int[]{4, 3, 1, 2, 4}));

    }

    public static long beautifulSubarrays(int[] nums) {

        int N = nums.length;

        int[] preSum = new int[N + 1];

        Map<Integer, Integer> rec = new HashMap<>();

        long ans = 0;

        rec.put(0, 1);

        for (int i = 1; i <= N; i++) {

            int num = nums[i - 1];

            preSum[i] = preSum[i - 1] ^ num;

            Integer old = rec.getOrDefault(preSum[i], 0);

            ans += old;

            rec.put(preSum[i], old + 1);

        }

        return ans;
    }


}
