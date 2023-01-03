package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-08-26 11:07
 */
public class Leet764 {

    public static void main(String[] args) {

    }

    public static int orderOfLargestPlusSign(int n, int[][] mines) {

        int[][] matrix = new int[n][n];

        for (int[] line : matrix) {
            Arrays.fill(line, 1);
        }

        for (int[] mine : mines) {
            int x = mine[0], y = mine[1];
            matrix[x][y] = 0;
        }

        int[][] top = new int[n][n];
        int[][] bottom = new int[n + 1][n];
        int[][] left = new int[n][n];
        int[][] right = new int[n][n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    top[i][j] = (i - 1 >= 0 ? top[i - 1][j] : 0) + 1;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    bottom[i][j] = bottom[i + 1][j] + 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    left[i][j] = (j - 1 >= 0 ? left[i][j - 1] : 0) + 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (matrix[i][j] == 1) {
                    ans = Math.max(
                            ans,
                            Math.min(
                                    top[i][j],
                                    Math.min(
                                            bottom[i][j],
                                            Math.min(
                                                    left[i][j],
                                                    right[i][j]
                                            )
                                    )
                            )
                    );
                }

            }

        }

        return ans;
    }


}
