package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-07-22 11:45
 */
public class Leet1277 {

    public static int countSquares(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;

        int[][] dp = new int[N + 1][M + 1];

        int ans = 0;

        for (int i = 1; i <= N; i++) {

            for (int j = 1; j <= M; j++) {

                if (matrix[i - 1][j - 1] != 1) {
                    continue;
                }

                // 左边
                // 左上
                // 右上
                dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                ans += dp[i][j];
            }

        }

        return ans;
    }

}
