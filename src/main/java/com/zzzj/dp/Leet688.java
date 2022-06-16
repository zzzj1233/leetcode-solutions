package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-06-16 19:52
 */
public class Leet688 {

    public static void main(String[] args) {
        System.out.println(knightProbability(3, 2, 0, 0));
    }

    public static double knightProbability(int n, int k, int row, int column) {
        return dfs(n, row, column, k) / Math.pow(8, k);
    }


    public static int dfs(int n, int i, int j, int k) {
        if (i < 0 || j < 0 || i >= n || j >= n) {
            return 0;
        }

        if (k == 0) {
            return 1;
        }

        return dfs(n, i + 1, j, k - 1) + dfs(n, i - 1, j, k - 1)
                + dfs(n, i, j + 1, k - 1) + dfs(n, i, j - 1, k - 1)
                + dfs(n, i + 1, j + 1, k - 1) + dfs(n, i - 1, j - 1, k - 1)
                + dfs(n, i + 1, j - 1, k - 1) + dfs(n, i - 1, j + 1, k - 1);

    }

}
