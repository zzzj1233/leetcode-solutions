package com.zzzj.dp;

public class Leet1911 {

    public static void main(String[] args) {
        System.out.println(maxAlternatingSum(new int[]{6, 2, 1, 2, 4, 5}));
        System.out.println(maxAlternatingSum(new int[]{5, 6, 7, 8}));
        System.out.println(maxAlternatingSum(new int[]{4, 2, 5, 3}));
    }

    public static long maxAlternatingSum(int[] nums) {
        int N = nums.length;

        // 0 = 偶数
        // 1 = 奇数
        long[][] dp = new long[N][2];

        dp[0][0] = nums[0];

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][1] + nums[i]), nums[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - nums[i]);
        }

        return dp[N - 1][0];
    }

}
