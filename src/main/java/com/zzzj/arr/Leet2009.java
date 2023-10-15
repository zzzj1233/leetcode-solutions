package com.zzzj.arr;


import java.util.Arrays;

public class Leet2009 {

    public static void main(String[] args) {

//        System.out.println(minOperations(new int[]{1, 10, 100, 1000}));

        // 4 5 8 8 9 9
//        System.out.println(minOperations(new int[]{8, 5, 9, 9, 8, 4}));

        System.out.println(minOperations(new int[]{18, 24, 26, 28, 29, 33, 33, 35, 41, 47}));

        // 24 [25] 26 [27] 28 29 [30] [31] [32]
    }

    public static int minOperations(int[] nums) {

        int cost = 0;

        int len = 1;

        int N = nums.length;

        int ans = Integer.MAX_VALUE;

        Arrays.sort(nums);

        for (int i = 1; i < N; i++) {

            int diff = nums[i] - nums[i - 1] - 1;

            if (diff == -1) {
                len++;
                cost++;
            } else if (diff == 0) {
                len++;
            } else {
                if (diff + cost <= len) {
                    cost += diff;
                    len += diff + 1;
                } else {
                    cost = i;
                    len = i + 1;
                }
            }

            if (len == N)
                ans = Math.min(ans, cost);
        }

        return ans;
    }

}
