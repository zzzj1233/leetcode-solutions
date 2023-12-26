package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-11-06 15:16
 */
public class Leet1444 {

    public static void main(String[] args) {

        System.out.println(ways(LeetUtils.convertString1("[\"A..\",\"AAA\",\"...\"]"), 3));

        System.out.println(ways(LeetUtils.convertString1("\"A..\",\"AA.\",\"...\""), 3));

        System.out.println(ways(LeetUtils.convertString1("\"A..\",\"A..\",\"...\""), 1));

    }

    static final int MOD = 1000000007;

    public static int ways(String[] str, int k) {

        int M = str.length;

        int N = str[0].length();

        int[][] pizza = new int[M][N];

        for (int i = 0; i < M; i++) for (int j = 0; j < N; j++) pizza[i][j] = str[i].charAt(j) == 'A' ? 1 : 0;

        int[][] preSum = new int[M + 1][N + 1];

        for (int i = 1; i <= M; i++)
            for (int j = 1; j <= N; j++)
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] + pizza[i - 1][j - 1] - preSum[i - 1][j - 1];

        int[][][] memo = new int[M][N][k + 1];

        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                Arrays.fill(memo[i][j], -1);

        return dfs(
                0,
                0,
                k,
                pizza,
                preSum,
                memo
        );
    }

    private static int dfs(
            int startRow,
            int startCol,
            int k,
            int[][] pizza,
            int[][] preSum,
            int[][][] memo
    ) {

        if (memo[startRow][startCol][k] != -1)
            return memo[startRow][startCol][k];

        int N = pizza[startRow].length;

        if (k == 1) return contains(preSum, startRow, startCol, pizza.length, N) ? 1 : 0;

        int res = 0;

        // 上面切一刀
        for (int i = startRow + 1; i < pizza.length; i++) {
            if (contains(preSum, startRow, startCol, i, N))
                res = (res + dfs(i, startCol, k - 1, pizza, preSum, memo)) % MOD;
        }

        // 左边切一刀
        for (int j = startCol + 1; j < N; j++) {
            if (contains(preSum, startRow, startCol, pizza.length, j))
                res = (res + dfs(startRow, j, k - 1, pizza, preSum, memo)) % MOD;
        }

        memo[startRow][startCol][k] = res;

        return res;
    }

    private static boolean contains(
            int[][] preSum,
            int startRow,
            int startCol,
            int row,
            int col
    ) {
        return preSum[row][col] - preSum[row][startCol] - preSum[startRow][col] + preSum[startRow][startCol] > 0;
    }

}
