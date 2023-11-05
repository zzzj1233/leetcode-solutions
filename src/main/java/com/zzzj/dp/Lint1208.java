package com.zzzj.dp;

import java.util.Arrays;

public class Lint1208 {

    public static void main(String[] args) {

        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));

        System.out.println(findTargetSumWays(new int[]{1, 1, 1}, 3));
    }

    public static int findTargetSumWays(int[] nums, int s) {

        int sum = Arrays.stream(nums).sum();

        if (s > sum)
            return 0;

        int N = nums.length;

        int[][] dp = new int[N + 1][(sum << 1) + 1];

        dp[0][sum] = 1;

        for (int i = 1; i <= N; i++) {

            int num = nums[i - 1];

            for (int j = 1; j < dp[i].length; j++) {
                if (j - num >= 0)
                    dp[i][j] += dp[i - 1][j - num];
                if (j + num < dp[i].length)
                    dp[i][j] += dp[i - 1][j + num];
            }

        }

        // System.out.println("dp[N] = " + Arrays.toString(dp[N]));

        return dp[N][s + sum];
    }

}
