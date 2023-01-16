package com.zzzj.greedy;

import java.util.HashMap;
import java.util.Map;

public class Leet1788 {

    public static void main(String[] args) {
        System.out.println(maximumBeauty(new int[]{1, 2, 3, 1, 2}));
    }

    public static int maximumBeauty(int[] flowers) {

        Map<Integer, Integer> counter = new HashMap<>();

        int N = flowers.length;

        int[] preSum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + Math.max(0, flowers[i - 1]);
            counter.put(flowers[i - 1], i - 1);
        }

        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            Integer index = counter.get(flowers[i]);
            if (index != i) {
                ans = Math.max(ans, Math.abs(preSum[i] - preSum[index + 1]));
            }
        }

        return ans;
    }

}
