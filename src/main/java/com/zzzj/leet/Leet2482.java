package com.zzzj.leet;

import java.util.Arrays;

public class Leet2482 {


    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(onesMinusZeros(LeetUtils.convertInts("[[0,1,1],[1,0,1],[0,0,1]]"))));
    }

    public static int[][] onesMinusZeros(int[][] grid) {

        int M = grid.length;
        int N = grid[0].length;

        int[] row = new int[M];
        int[] col = new int[N];

        for (int i = 0; i < M; i++) {

            int one = 0;

            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    one++;
                }
            }

            row[i] = one;
        }

        for (int i = 0; i < N; i++) {

            int one = 0;

            for (int j = 0; j < M; j++) {
                if (grid[j][i] == 1) {
                    one++;
                }
            }

            col[i] = one;
        }

        int[][] ans = new int[M][N];

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                ans[i][j] = one(row, col, i, j) - zero(row, col, i, j);

            }

        }

        return ans;
    }

    public static int one(int[] row, int[] col, int x, int y) {
        return row[x] + col[y];
    }

    public static int zero(int[] row, int[] col, int x, int y) {
        int M = row.length;
        int N = col.length;
        return (M - row[x]) + (N - col[y]);
    }

}
