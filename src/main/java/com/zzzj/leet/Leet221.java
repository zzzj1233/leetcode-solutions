package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-06 15:49
 */
public class Leet221 {

    public static void main(String[] args) {
//        System.out.println(maximalSquare(LeetUtils.convertChars("[[1, 1, 1, 0], [0, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]]")));
//        System.exit(0);

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

        int max = 0;

        for (int i = 0; i < N; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                max = 1;
            }
        }

        for (int i = 0; i < M; i++) {
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                max = 1;
            }
        }

        for (int i = 1; i < N; i++) {

            for (int j = 1; j < M; j++) {
                if (matrix[i][j] != '1') {
                    continue;
                }

                // 左上角
                int lt = dp[i - 1][j - 1];

                dp[i][j] = 1;

                max = Math.max(max, 1);

                if (lt == 0) {
                    continue;
                }

                // 看看是否能构成一个更大的正方形?
                if (dp[i - 1][j] >= lt && dp[i][j - 1] >= lt) {
                    dp[i][j] = lt + 1;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }

                max = Math.max(max, dp[i][j]);
            }

        }

        return max * max;
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
