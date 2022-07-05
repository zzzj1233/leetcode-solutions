package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-07-05 14:12
 */
public class Leet2169 {

    // 最大和的子矩阵
    public static int[] getMaxMatrix(int[][] matrix) {

        // 先求出二维的前缀和，然后固定上下两条边，之后从左往右遍历一遍，找到固定上下边下的最大子序和即可

        int N = matrix.length;
        int M = matrix[0].length;

        // r1,c1,r2,c2
        int[] ans = new int[4];

        int[][] preSum = new int[N + 1][M];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                preSum[i][j] = preSum[i - 1][j] + matrix[i - 1][j];
            }
        }

        int globalMax = matrix[0][0];

        // 两层for循环穷举所有的上下两条边
        for (int i = 0; i < N; i++) {

            for (int j = i; j < N; j++) {

                // 从左到右
                int[] arr = new int[M];

                for (int k = 0; k < M; k++) {
                    arr[k] = preSum[j + 1][k] - preSum[i][k];
                }

                int sum = arr[0];

                int max = arr[0];

                int start = 0;

                for (int k = 1; k < M; k++) {
                    if (arr[k] > sum + arr[k]) {
                        sum = arr[k];
                        start = k;
                    } else {
                        sum += arr[k];
                    }

                    max = Math.max(max, sum);

                    if (max > globalMax) {
                        globalMax = max;
                        ans[0] = i;
                        ans[1] = start;
                        ans[2] = j;
                        ans[3] = k;
                    }

                }

            }

        }

        return ans;
    }

}
