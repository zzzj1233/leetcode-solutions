package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-04-08 16:00
 */
public class Leet1402 {

    public static void main(String[] args) {
//        System.out.println(maxSatisfaction(new int[]{-1, -8, 0, 5, -9}));
//        System.out.println(maxSatisfaction(new int[]{4, 3, 2}));
//        System.out.println(maxSatisfaction(new int[]{-1, -4, -5}));

        System.out.println("======================================");

        System.out.println(dp(new int[]{-1, -8, 0, 5, -9}));
        System.out.println(dp(new int[]{4, 3, 2}));
        System.out.println(dp(new int[]{-1, -4, -5}));
    }

    public static int maxSatisfaction(int[] satisfaction) {
        return dp(satisfaction);
    }

    public static int dp(int[] satisfaction) {
        Arrays.sort(satisfaction);

        int N = satisfaction.length;

        int[][] dp = new int[N][N + 1];

        for (int i = 0; i <= N; i++) {
            dp[N - 1][i] = satisfaction[N - 1] * i;
        }

        for (int i = 0; i < N; i++) {
            dp[i][N] = satisfaction[i] * N;
        }

        for (int i = N - 2; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                int ignore = dp[i + 1][j];
                int done = satisfaction[i] * j + dp[i + 1][j + 1];
                dp[i][j] = Math.max(ignore, done);
            }
        }

        return Math.max(dp[0][0], dp[0][1]);
    }

    public static int dfs(int[] satisfaction, int i, int k) {
        if (i == satisfaction.length) {
            return 0;
        }
        // 不做
        int ignore = dfs(satisfaction, i + 1, k);

        // 做
        int done = dfs(satisfaction, i + 1, k + 1) + satisfaction[i] * k;

        return Math.max(ignore, done);
    }

    public static int getSize() {
        System.out.println("size");
        return 10;
    }

}
