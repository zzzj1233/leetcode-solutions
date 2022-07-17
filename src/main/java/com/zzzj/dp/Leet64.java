package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author Zzzj
 * @create 2022-07-02 17:10
 */
public class Leet64 {

    public static void main(String[] args) {
        System.out.println(minPathSum(LeetUtils.convertInts("[[1,3,1],[1,5,1],[4,2,1]]")));
    }

    public static int minPathSum(int[][] grid) {
        return dp(grid);
    }

    public static int dp(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        int[] dp = new int[M];

        dp[M - 1] = grid[N - 1][M - 1];

        for (int i = M - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] + grid[N - 1][i];
        }

        for (int i = N - 2; i >= 0; i--) {
            dp[M - 1] += grid[i][M - 1];

            for (int j = M - 2; j >= 0; j--) {
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
            }

        }

        return dp[0];
    }

    public static int pick(int[][] dp, int i, int j) {
        if (i >= dp.length || j >= dp[i].length) {
            return Integer.MAX_VALUE;
        }
        return dp[i][j];
    }


    public static int dfs(int[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid[i].length) {
            return Integer.MAX_VALUE;
        }
        if (i == grid.length - 1 && j == grid[i].length - 1) {
            return grid[i][j];
        }

        int min = Math.min(dfs(grid, i + 1, j), dfs(grid, i, j + 1));

        return grid[i][j] + min;
    }

}