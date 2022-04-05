package com.zzzj.hot;

import com.zzzj.leet.LeetUtils;

/**
 * @author Zzzj
 * @create 2022-04-05 21:49
 */
public class Leet64 {

    public static void main(String[] args) {
        System.out.println(minPathSum(LeetUtils.convertInts("[[1,3,1],[1,5,1],[4,2,1]]")));
        System.out.println(minPathSum(LeetUtils.convertInts("[[1,2,3],[4,5,6]]")));
    }

    public static int minPathSum(int[][] grid) {
        // return dfs(grid, grid.length, grid[0].length, 0, 0);
        return dp(grid);
    }

    public static int dp(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        int[][] dp = new int[N][M];

        dp[N - 1][M - 1] = grid[N - 1][M - 1];

        for (int i = N - 1; i >= 0; i--) {

            for (int j = M - 1; j >= 0; j--) {

                if (i + 1 >= N && j + 1 >= M) {
                    dp[i][j] = grid[i][j];
                    continue;
                }

                int bottomResult = Integer.MAX_VALUE;
                int rightResult = Integer.MAX_VALUE;

                if (i + 1 < N) {
                    bottomResult = dp[i + 1][j];
                }

                if (j + 1 < M) {
                    rightResult = dp[i][j + 1];
                }

                dp[i][j] = Math.min(bottomResult, rightResult) + grid[i][j];
            }

        }

        return dp[0][0];
    }

    public static int dfs(int[][] grid, int N, int M, int i, int j) {
        // 只可以往右或者往下走
        int bottomResult = Integer.MAX_VALUE;
        int rightResult = Integer.MAX_VALUE;

        // 终点
        if (i + 1 >= N && j + 1 >= M) {
            return grid[i][j];
        }

        if (i + 1 < N) {
            bottomResult = dfs(grid, N, M, i + 1, j);
        }

        if (j + 1 < M) {
            rightResult = dfs(grid, N, M, i, j + 1);
        }

        return Math.min(bottomResult, rightResult) + grid[i][j];
    }

}
