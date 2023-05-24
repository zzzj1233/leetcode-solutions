package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-08-23 21:08
 */
public class Leet542 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(updateMatrix(LeetUtils.convertInts("[[0,0,0],[0,1,0],[0,0,0]]"))));

        System.out.println(Arrays.deepToString(updateMatrix(LeetUtils.convertInts("[[0,0,0],[0,1,0],[1,1,1]]"))));

        System.out.println(Arrays.deepToString(updateMatrix(LeetUtils.convertInts("[[0],[0],[0],[0],[0]]"))));
    }

    public static int[][] updateMatrix(int[][] mat) {

        int M = mat.length;

        int N = mat[0].length;

        int[][] dp = new int[M][N];

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                if (mat[i][j] == 0) continue;

                dp[i][j] = Math.min(pick(i - 1, j, dp), pick(i, j - 1, dp));
            }

        }

        for (int i = M - 1; i >= 0; i--) {

            for (int j = N - 1; j >= 0; j--) {

                if (mat[i][j] == 0) continue;

                dp[i][j] = Math.min(dp[i][j],
                        Math.min(pick(i + 1, j, dp), pick(i, j + 1, dp))
                );
            }

        }

        return dp;
    }

    public static int pick(int x, int y, int[][] dp) {
        if (x < 0 || y < 0 || x >= dp.length || y >= dp[x].length) {
            return Integer.MAX_VALUE;
        }
        if (dp[x][y] == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return dp[x][y] + 1;
    }

}
