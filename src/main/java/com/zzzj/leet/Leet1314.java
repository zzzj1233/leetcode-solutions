package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-12-08 12:28
 */
public class Leet1314 {


    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(matrixBlockSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 1)));
    }

    public static int[][] matrixBlockSum(int[][] mat, int k) {

        int M = mat.length;

        int N = mat[0].length;

        int[][] ans = new int[M][N];

        // 前缀和
        int[][] preSum = new int[M + 1][N + 1];

        for (int i = 1; i <= M; i++) {

            for (int j = 1; j <= N; j++) {

                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + mat[i - 1][j - 1];

            }

        }

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                int rowStart = Math.max(0, i - k) + 1;
                int rowEnd = Math.min(M - 1, i + k) + 1;

                int colStart = Math.max(0, j - k) + 1;
                int colEnd = Math.min(N - 1, j + k) + 1;

                ans[i][j] = preSum[rowEnd][colEnd] - preSum[rowEnd][colStart - 1] - preSum[rowStart - 1][colEnd] + preSum[rowStart - 1][colStart - 1];
            }

        }

        return ans;
    }


}
