package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-06 15:49
 */
public class Leet221 {

    public static void main(String[] args) {
//        System.out.println(maximalSquare(LeetUtils.convertChars("[[1, 1, 1, 0], [0, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]]")));
        System.out.println(maximalSquare(LeetUtils.convertChars("[[0],[1]]")));
        System.exit(0);

        for (int i = 0; i < 10000; i++) {
            int[][] ints = LeetUtils.randomMatrix(10, 10, 0, 2);
            char[][] chars = LeetUtils.intsToChars(ints);
            if (maximalSquare(chars) != right(chars)) {
                System.out.println(Arrays.deepToString(chars));
                System.out.println(maximalSquare(chars));
                System.out.println(right(chars));
                return;
            }
        }
    }

    // 最大正方形
    public static int maximalSquare(char[][] matrix) {

        int N = matrix.length;

        int M = matrix[0].length;

        int[][] dp = new int[N][M];

        int ans = 0;

        for (int i = 0; i < M; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            ans = Math.max(ans, dp[0][i]);
        }

        for (int i = 0; i < N; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            ans = Math.max(ans, dp[i][0]);
        }

        for (int i = 1; i < N; i++) {

            for (int j = 1; j < M; j++) {

                // 左边
                // 上边
                // 左上角
                if (matrix[i][j] != '1') {
                    continue;
                }

                int left = dp[i][j - 1];
                int top = dp[i - 1][j];
                int topLeft = dp[i - 1][j - 1];

                dp[i][j] = Math.min(left, Math.min(top, topLeft)) + 1;

                ans = Math.max(ans, dp[i][j] * dp[i][j]);
            }

        }

        return ans;
    }

    public static int right(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }


}
