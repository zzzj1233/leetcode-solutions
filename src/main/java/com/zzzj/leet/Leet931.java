package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-07-21 22:41
 */
public class Leet931 {


    public static void main(String[] args) {
        System.out.println(minFallingPathSum(LeetUtils.convertInts("[[2,1,3],[6,5,4],[7,8,9]]")));
    }

    public static int minFallingPathSum(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;

        int[][] dp = new int[N][M];

        for (int i = 0; i < M; i++) {
            dp[0][i] = matrix[0][i];
        }

        for (int i = 1; i < N; i++) {

            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + matrix[i][0];

            for (int j = 1; j < M - 1; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i - 1][j + 1])) + matrix[i][j];
            }

            dp[i][M - 1] = Math.min(dp[i - 1][M - 1], dp[i - 1][M - 2]) + matrix[i][M - 1];

        }

        int ans = dp[N - 1][0];

        for (int i = 1; i < M; i++) {
            ans = Math.min(ans, dp[N - 1][i]);
        }

        return ans;
    }

}
