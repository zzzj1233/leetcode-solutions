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
        // 只能往下或者往右走,求最短路径

        int N = grid.length;
        int M = grid[0].length;

        int[][] dp = new int[N][M];

        dp[0][0] = grid[0][0];

        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < N; i++) {

            for (int j = 1; j < M; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }

        }

        return dp[N - 1][M - 1];
    }


}
