package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-07-05 15:42
 */
public class Leet363 {

    public static void main(String[] args) {
//        System.out.println(maxSumSubmatrix(LeetUtils.convertInts("[[1,0,1],[0,-2,3]]"), 2));
        System.out.println(maxSumSubmatrix(LeetUtils.convertInts("[[-1,-1,-1]]"), 3));
    }


    public static int maxSumSubmatrix(int[][] matrix, int k) {

        int N = matrix.length;
        int M = matrix[0].length;

        int[][] preSum = new int[N + 1][M];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                preSum[i][j] = preSum[i - 1][j] + matrix[i - 1][j];
            }
        }

        int globalMax = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {

            for (int j = i; j < N; j++) {

                int[] arr = new int[M];

                for (int l = 0; l < M; l++) {
                    arr[l] = preSum[j + 1][l] - preSum[i][l];
                }

                // 穷举子数组

                for (int l = 0; l < M; l++) {
                    int sum = 0;

                    for (int m = l; m < M; m++) {
                        sum += arr[m];

                        if (sum <= k && sum > globalMax) {
                            globalMax = sum;
                        }

                    }

                }

            }

        }

        return globalMax;
    }

}
