package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-06-16 19:52
 */
public class Leet688 {

    public static void main(String[] args) {
        System.out.println(knightProbability(8, 30, 6, 4));
    }

    // 8
    // 30
    // 6
    // 4
    public static double knightProbability(int n, int k, int row, int column) {
        return dp(n, row, column, k);
    }

    public static double dp(int n, int I, int J, int K) {
        double[][] dp = new double[n][n];

        for (int l = 0; l < n; l++) {
            for (int m = 0; m < n; m++) {
                dp[l][m] = 1;
            }
        }

        for (int k = 1; k <= K; k++) {
            double[][] newDp = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    newDp[i][j] = pick(dp, i + 2, j + 1, n) + pick(dp, i + 2, j - 1, n)
                            + pick(dp, i - 1, j + 2, n) + pick(dp, i + 1, j + 2, n)
                            + pick(dp, i - 2, j + 1, n) + pick(dp, i - 2, j - 1, n)
                            + pick(dp, i - 1, j - 2, n) + pick(dp, i + 1, j - 2, n);
                }
            }
            dp = newDp;
        }

        return dp[I][J];
    }

    public static double pick(double[][] dp, int i, int j, int n) {
        if (i < 0 || j < 0 || i >= n || j >= n) {
            return 0;
        }
        return dp[i][j] / 8;
    }

    public static int dfs(int n, int i, int j, int k) {
        if (i < 0 || j < 0 || i >= n || j >= n) {
            return 0;
        }

        if (k == 0) {
            return 1;
        }

        return dfs(n, i + 2, j + 1, k - 1) + dfs(n, i + 2, j - 1, k - 1)
                + dfs(n, i - 1, j + 2, k - 1) + dfs(n, i + 1, j + 2, k - 1)
                + dfs(n, i - 2, j + 1, k - 1) + dfs(n, i - 2, j - 1, k - 1)
                + dfs(n, i - 1, j - 2, k - 1) + dfs(n, i + 1, j - 2, k - 1);

    }

}
