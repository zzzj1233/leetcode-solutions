package com.zzzj.bit;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-08-02 12:21
 */
public class Leet861 {

    public static void main(String[] args) {
        System.out.println(matrixScore(LeetUtils.convertInts("[[1,0,0,0],[0,0,0,1],[0,0,0,1],[0,0,0,1]]")));

        for (int i = 0; i < 10000; i++) {
            final int[][] ints = LeetUtils.randomMatrix(10, 10, 0, 2);

            int[][] copy = Arrays.copyOfRange(ints, 0, ints.length);

            if (matrixScore(ints) != right(copy)) {
                System.out.println("Error");
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int matrixScore(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        int[] nums = new int[N];

        for (int j = 0; j < M; j++) {

            int zeroCount = 0;

            for (int i = 0; i < N; i++) {
                if (grid[i][j] == 0) {
                    zeroCount++;
                }
            }

            int oneCount = N - zeroCount;

            if (zeroCount > oneCount) {
                for (int i = 0; i < N; i++) {
                    if (grid[i][j] == 0) {
                        nums[i] |= 1 << (M - j - 1);
                        grid[i][j] = 1;
                    } else {
                        grid[i][j] = 0;
                    }
                }
            } else {
                for (int i = 0; i < N; i++) {
                    if (grid[i][j] == 1) {
                        nums[i] |= 1 << (M - j - 1);
                    }
                }
            }

            if (j == 0) {
                // 高位是1就反转
                for (int i = 0; i < N; i++) {
                    if (grid[i][0] == 0) {
                        for (int k = 0; k < M; k++) {
                            grid[i][k] = grid[i][k] == 1 ? 0 : 1;
                        }
                        nums[i] |= 1 << (M - 1);
                    }
                }
            }

        }

        return Arrays.stream(nums).sum();
    }

    public static int right(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ret = m * (1 << (n - 1));

        for (int j = 1; j < n; j++) {
            int nOnes = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][0] == 1) {
                    nOnes += grid[i][j];
                } else {
                    nOnes += (1 - grid[i][j]); // 如果这一行进行了行反转，则该元素的实际取值为 1 - grid[i][j]
                }
            }
            int k = Math.max(nOnes, m - nOnes);
            ret += k * (1 << (n - j - 1));
        }
        return ret;
    }

}
