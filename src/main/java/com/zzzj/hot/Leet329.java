package com.zzzj.hot;

/**
 * @author zzzj
 * @create 2022-05-05 17:47
 */
public class Leet329 {

    public static int longestIncreasingPath(int[][] matrix) {
        int ans = 0;


        int[][] cache = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                ans = Math.max(ans, dfs(matrix, i, j, cache));

            }

        }
        return ans;
    }

    public static int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] > 0) {
            return cache[i][j];
        }

        int value = matrix[i][j];

        int ans = 0;

        if (i + 1 < matrix.length && matrix[i + 1][j] > value) {
            ans = dfs(matrix, i + 1, j, cache);
        }

        if (i - 1 >= 0 && matrix[i - 1][j] > value) {
            ans = Math.max(ans, dfs(matrix, i - 1, j, cache));
        }

        if (j - 1 >= 0 && matrix[i][j - 1] > value) {
            ans = Math.max(ans, dfs(matrix, i, j - 1, cache));
        }

        if (j + 1 < matrix[i].length && matrix[i][j + 1] > value) {
            ans = Math.max(ans, dfs(matrix, i, j + 1, cache));
        }

        cache[i][j] = ans + 1;
        return ans + 1;
    }

}
