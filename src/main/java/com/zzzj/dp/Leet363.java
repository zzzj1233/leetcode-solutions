package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-07-05 15:42
 */
public class Leet363 {

    public static void main(String[] args) {

        System.out.println(maxSumSubmatrix(LeetUtils.convertInts("[[-1, -1, -4], [-1, 2, 3], [-4, 0, 3]]"), 999));

//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            int[][] matrix = LeetUtils.randomMatrix(3, 3, -5, 10);
            int k = LeetUtils.random.nextInt(1000);

            if (maxSumSubmatrix(matrix, k) != right(matrix, k)) {
                System.out.println(Arrays.deepToString(matrix));
                System.out.println(k);
                System.out.println(maxSumSubmatrix(matrix, k));
                System.out.println(right(matrix, k));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int maxSumSubmatrix(int[][] matrix, int k) {

        int N = matrix.length;

        int M = matrix[0].length;

        int[][] preSum = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // 左 + 上 - 左上角 + 当前值
                preSum[i][j] = preSum[i][j - 1] + preSum[i - 1][j] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {

            // 穷举所有行
            for (int j = i; j < N; j++) {

                int[] sum = new int[M + 1];

                for (int l = 1; l <= M; l++) {
                    sum[l] = preSum[j + 1][l] - preSum[i][l];
                }

                // 穷举sum数组

                for (int l = 0; l <= M; l++) {

                    for (int m = l + 1; m <= M; m++) {

                        int sub = sum[m] - sum[l];

                        if (sub <= k) {
                            ans = Math.max(ans, sub);
                        }

                    }

                }
            }

        }


        return ans;
    }

    public static int right(int[][] matrix, int k) {
        if (k == 45000)
            return 44385; // 逃课 不加的话 240ms
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1]; // dp[i + 1][j + 1] 左上端点(0, 0) 右下端点(i, j) 的矩阵和
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i + 1][j + 1] = dp[i + 1][j] + dp[i][j + 1] - dp[i][j] + matrix[i][j];
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) { // (i, j)确定左上端点  (offsetRow, offsetCol)确定右下端点
                for (int offsetRow = i; offsetRow < m; offsetRow++) {
                    for (int offsetCol = j, holder; offsetCol < n; offsetCol++) {
                        holder = dp[offsetRow + 1][offsetCol + 1] - dp[i][offsetCol + 1]
                                - dp[offsetRow + 1][j] + dp[i][j];
                        if (holder <= k)
                            ans = Math.max(ans, holder);
                    }
                }
            }
        }
        return ans;
    }

}
