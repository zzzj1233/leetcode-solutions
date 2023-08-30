package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;


/**
 * @author zzzj
 * @create 2023-08-25 17:09
 */
public class Leet2435 {

    public static void main(String[] args) {
        System.out.println(numberOfPaths(LeetUtils.convertInts("[[0]]"), 1));
    }

    public static int numberOfPaths(int[][] grid, int k) {

        final int MOD = 1000000007;

        int M = grid.length;

        int N = grid[0].length;

        if (M * N == 1)
            return grid[0][0] % k == 0 ? 1 : 0;

        int[][][] dp = new int[M][N][k + 1];

        dp[M - 1][N - 1][k - (grid[M - 1][N - 1] % k)] = 1;

        // 最后一行
        for (int i = N - 2; i >= 0; i--) {
            int value = grid[M - 1][i];
            int x = k - (value % k);

            for (int j = 0; j <= k; j++) {
                dp[M - 1][i][(x + j) % k] += dp[M - 1][i + 1][j] % MOD;
                dp[M - 1][i][(x + j) % k] %= MOD;
            }

        }

        // 最后一列
        for (int i = M - 2; i >= 0; i--) {
            int value = grid[i][N - 1];
            int x = k - (value % k);

            for (int j = 0; j <= k; j++) {
                dp[i][N - 1][(x + j) % k] += dp[i + 1][N - 1][j] % MOD;
                dp[i][N - 1][(x + j) % k] %= MOD;
            }

        }

        for (int i = M - 2; i >= 0; i--) {

            for (int j = N - 2; j >= 0; j--) {
                int value = grid[i][j];
                int x = k - (value % k);

                // 右边
                for (int z = 0; z <= k; z++) {
                    dp[i][j][(x + z) % k] += dp[i][j + 1][z] % MOD;
                    dp[i][j][(x + z) % k] %= MOD;
                }
                // 下面
                for (int z = 0; z <= k; z++) {
                    dp[i][j][(x + z) % k] += dp[i + 1][j][z] % MOD;
                    dp[i][j][(x + z) % k] %= MOD;
                }
            }

        }

        return dp[0][0][0];
    }

}
