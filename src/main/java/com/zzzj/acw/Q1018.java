package com.zzzj.acw;

import java.util.Scanner;

public class Q1018 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int M = scanner.nextInt();

        int[][] cost = new int[M][M];

        int[][] dp = new int[M][M];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                cost[i][j] = scanner.nextInt();
            }
        }

        dp[0][0] = cost[0][0];

        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i - 1] + cost[0][i];
            dp[i][0] = dp[i - 1][0] + cost[i][0];
        }

        for (int i = 1; i < M; i++) {

            for (int j = 1; j < M; j++) {

                dp[i][j] = Math.min(
                        dp[i - 1][j],
                        dp[i][j - 1]
                ) + cost[i][j];
            }

        }

        System.out.println(dp[M - 1][M - 1]);
    }

}
