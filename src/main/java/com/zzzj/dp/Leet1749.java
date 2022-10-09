package com.zzzj.dp;

public class Leet1749 {

    public static void main(String[] args) {
        System.out.println(maxAbsoluteSum(new int[]{2, -5, 1, -4, 3, -2}));
    }

    public static int maxAbsoluteSum(int[] nums) {

        int N = nums.length;

        // 0 = 最大值
        // 1 = 最小值
        int[][] dp = new int[N + 1][2];

        int ans = 0;

        for (int i = 1; i <= N; i++) {
            int num = nums[i - 1];

            dp[i][0] = Math.max(0, Math.max(num, Math.max(dp[i - 1][0] + num, dp[i - 1][1] + num)));
            dp[i][1] = Math.min(0, Math.min(num, Math.min(dp[i - 1][0] + num, dp[i - 1][1] + num)));

            ans = Math.max(ans, Math.max(Math.abs(dp[i][0]), Math.abs(dp[i][1])));
        }

        return ans;
    }

}
