package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2022-12-05 11:44
 */
public class Leet1901 {

    public static int[] findPeakGrid(int[][] mat) {

        int M = mat.length;

        int N = mat[0].length;

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                if (dfs(i, j, mat)) {
                    return new int[]{i, j};
                }

            }

        }

        return null;
    }

    public static boolean dfs(int row, int col, int[][] mat) {
        int value = mat[row][col];

        return gt(value, row, col - 1, mat) && gt(value, row, col + 1, mat)
                && gt(value, row + 1, col, mat) && gt(value, row - 1, col, mat);
    }

    public static boolean gt(int value, int row, int col, int[][] mat) {
        if (row < 0 || col < 0) {
            return true;
        }
        if (row >= mat.length || col >= mat[row].length) {
            return true;
        }
        return value > mat[row][col];
    }

}
