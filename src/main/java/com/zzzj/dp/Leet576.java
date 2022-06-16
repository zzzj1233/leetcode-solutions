package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-06-16 19:27
 */
public class Leet576 {

    public static void main(String[] args) {
        System.out.println(findPaths(2, 2, 2, 0, 0));
        System.out.println(findPaths(1, 3, 3, 0, 1));
    }

    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        return dfs(m, n, startRow, startColumn, maxMove, new Integer[m][n][maxMove + 1]) % 1000000007;
    }


    public static int dfs(int m, int n, int i, int j, int move, Integer[][][] memo) {
        // 一种方法
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return 1;
        }
        if (move == 0) {
            return 0;
        }

        if (memo[i][j][move] != null) {
            return memo[i][j][move];
        }

        int result = 0;

        result = (result + dfs(m, n, i + 1, j, move - 1, memo)) % 1000000007;
        result = (result + dfs(m, n, i - 1, j, move - 1, memo)) % 1000000007;
        result = (result + dfs(m, n, i, j + 1, move - 1, memo)) % 1000000007;
        result = (result + dfs(m, n, i, j - 1, move - 1, memo)) % 1000000007;

        memo[i][j][move] = result;

        return memo[i][j][move];

    }

}
