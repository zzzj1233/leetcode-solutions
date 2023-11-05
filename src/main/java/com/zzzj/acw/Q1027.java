package com.zzzj.acw;

import com.zzzj.leet.LeetUtils;

import java.util.Scanner;

public class Q1027 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(LeetUtils.checkSource("7\n" +
                "1 3 2\n" +
                "1 4 3\n" +
                "2 3 3\n" +
                "3 3 3\n" +
                "5 5 4\n" +
                "6 5 4\n" +
                "7 3 2\n" +
                "7 5 4\n" +
                "0 0 0"));

        int n = scanner.nextInt();

        int N = 11;

        int[][] grid = new int[N][N];

        while (scanner.hasNextInt()) {

            int r = scanner.nextInt();

            int c = scanner.nextInt();

            grid[r][c] = scanner.nextInt();
        }

        int[][][][] dp = new int[N][N][N][N];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int x = 1; x <= n; x++) {
                    for (int y = 1; y <= n; y++) {
                        int case1 = dp[i - 1][j][x - 1][y];
                        int case2 = dp[i - 1][j][x][y - 1];
                        int case3 = dp[i][j - 1][x - 1][y];
                        int case4 = dp[i][j - 1][x][y - 1];
                        int val = i == x && j == y ? grid[i][j] : grid[i][j] + grid[x][y];
                        dp[i][j][x][y] = Math.max(
                                Math.max(case1, case2),
                                Math.max(case3, case4)
                        ) + val;
                    }
                }
            }
        }

        System.out.println(dp[n][n][n][n]);
    }

}
