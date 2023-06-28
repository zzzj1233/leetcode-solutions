package com.zzzj.matrix;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-06-27 16:16
 */
public class Leet2536 {

    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(rangeAddQueries(3, LeetUtils.convertInts("[[1,1,2,2],[0,0,1,1]]"))));

    }

    public static int[][] rangeAddQueries(int n, int[][] queries) {

        int[][] grid = new int[n][n];

        int[][] preSum = new int[n][n];

        for (int[] query : queries) {

            int r1 = query[0];

            int c1 = query[1];

            int r2 = query[2];

            int c2 = query[3];

            preSum[r1][c1]++;
            grid[r1][c1]++;
            grid[r2][c2]++;

            if (r2 + 1 < n) preSum[r2 + 1][c1]--;
            if (c2 + 1 < n) preSum[r1][c2 + 1]--;
        }

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (i - 1 >= 0) {
                    grid[i][j] += preSum[i - 1][j];
                }

                if (j - 1 >= 0) {
                    grid[i][j] += preSum[i][j - 1];
                }

                if (i - 1 >= 0 && j - 1 >= 0) {
                    grid[i][j] -= preSum[i - 1][j - 1];
                }

            }

        }

        return grid;
    }

}
