package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-13 18:52
 */
public class Leet562 {

    public static void main(String[] args) {
        System.out.println(longestLine(LeetUtils.convertInts("[[0,1,1,0],[0,1,1,0],[0,0,0,1]]")));
        System.out.println(longestLine(LeetUtils.convertInts("[[1,1,1,1],[0,1,1,0],[0,0,0,1]]")));

//        System.out.println(longestLine(LeetUtils.convertInts("[[0, 0, 1, 0], [0, 1, 0, 0]]")));
        // System.exit(0);
        for (int i = 0; i < 1000; i++) {
            final int[][] matrix = LeetUtils.randomMatrix(10, 20, 0, 2);
            if (longestLine(matrix) != right(matrix)) {
                System.out.println("Error");
                System.out.println(Arrays.deepToString(matrix));
                System.out.println(longestLine(matrix));
                System.out.println("right ---> " + right(matrix));
                return;
            }
        }
    }

    public static int longestLine(int[][] mat) {
        int N = mat.length;
        int M = mat[0].length;

        int[][][] dp = new int[N][M][4];

        dp[0][0][0] = mat[0][0];
        dp[0][0][1] = mat[0][0];
        dp[0][0][2] = mat[0][0];
        dp[0][0][3] = mat[0][0];

        int ans = mat[0][0];

        for (int i = 1; i < M; i++) {
            dp[0][i][0] = mat[0][i] == 1 ? dp[0][i - 1][0] + 1 : 0;
            dp[0][i][1] = mat[0][i];
            dp[0][i][2] = mat[0][i];
            dp[0][i][3] = mat[0][i];
            ans = Math.max(ans, dp[0][i][0]);
        }

        for (int i = 1; i < N; i++) {

            for (int j = 0; j < M; j++) {

                // 和上面拿1
                // 和左边拿0
                // 和左上角拿2
                // 和右上角拿3
                dp[i][j][0] = mat[i][j] == 1 ? pick(i, j - 1, 0, M, dp) + 1 : 0;
                dp[i][j][1] = mat[i][j] == 1 ? dp[i - 1][j][1] + 1 : 0;
                dp[i][j][2] = mat[i][j] == 1 ? pick(i - 1, j - 1, 2, M, dp) + 1 : 0;
                dp[i][j][3] = mat[i][j];
                if (j + 1 < M && mat[i][j] == 1) {
                    dp[i][j][3] = dp[i - 1][j + 1][3] + 1;
                }
                ans = Math.max(ans, dp[i][j][0]);
                ans = Math.max(ans, dp[i][j][1]);
                ans = Math.max(ans, dp[i][j][2]);
                ans = Math.max(ans, dp[i][j][3]);
            }

        }

        return ans;
    }

    public static int pick(int i, int j, int k, int M, int[][][] dp) {
        if (j < 0) {
            return 0;
        }
        return dp[i][j][k];
    }

    public static int right(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0)
            return 0;
        int ans = 0;
        int[] horizontal = new int[M[0].length];
        int[] vertical = new int[M[0].length];
        int[] diagonal = new int[M[0].length];
        int[] antidiagonal = new int[M[0].length];
        for (int i = 0; i != M.length; ++i) {
            int[] vertical_new = new int[M[0].length];
            int[] diagonal_new = new int[M[0].length];
            int[] antidiagonal_new = new int[M[0].length];
            for (int j = 0; j != M[0].length; ++j) {
                if (M[i][j] == 0) {
                    horizontal[j] = 0;
                    vertical_new[j] = 0;
                    diagonal_new[j] = 0;
                    antidiagonal_new[j] = 0;
                } else {
                    horizontal[j] = j > 0 ? horizontal[j - 1] + 1 : 1;
                    vertical_new[j] = i > 0 ? vertical[j] + 1 : 1;
                    diagonal_new[j] = i > 0 && j > 0 ? diagonal[j - 1] + 1 : 1;
                    antidiagonal_new[j] = i > 0 && j < M[0].length - 1 ? antidiagonal[j + 1] + 1 : 1;
                    ans = Math.max(ans, horizontal[j]);
                    ans = Math.max(ans, vertical_new[j]);
                    ans = Math.max(ans, diagonal_new[j]);
                    ans = Math.max(ans, antidiagonal_new[j]);
                }
            }
            vertical = vertical_new;
            diagonal = diagonal_new;
            antidiagonal = antidiagonal_new;
        }
        return ans;
    }


}
