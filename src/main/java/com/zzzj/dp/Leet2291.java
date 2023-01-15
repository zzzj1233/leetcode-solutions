package com.zzzj.dp;


public class Leet2291 {

    public static void main(String[] args) {
        System.out.println(maximumProfit(new int[]{5, 4, 6, 2, 3}, new int[]{8, 5, 4, 3, 5}, 10));
    }

    public static int maximumProfit(int[] present, int[] future, int budget) {
        // return dfs(present, future, budget, 0);
        int N = present.length;

        int[][] dp = new int[N + 1][budget + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = budget; j >= 0; j--) {
                if (j < present[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    int ways1 = dp[i - 1][j - present[i - 1]] + (future[i - 1] - present[i - 1]);
                    int ways2 = dp[i - 1][j];
                    dp[i][j] = Math.max(ways1, ways2);
                }
            }
        }

        return dp[N][budget];
    }

    private static int dfs(int[] present, int[] future, int budget, int index) {
        if (index >= present.length) {
            return 0;
        }

        if (budget < present[index]) {
            return dfs(present, future, budget, index + 1);
        }

        return Math.max(dfs(present, future, budget - present[index], index + 1) + (future[index] - present[index]),
                dfs(present, future, budget, index + 1)
        );
    }

}
