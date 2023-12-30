package com.zzzj.acw;

import java.util.Scanner;

public class Q1013 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int M = scanner.nextInt();

        int[][] w = new int[N][M + 1];

        for (int i = 0; i < N; i++)
            for (int j = 1; j <= M; j++)
                w[i][j] = scanner.nextInt();

        int[][] dp = new int[N + 1][M + 1];

        for (int i = N - 1; i >= 0; i--) {

            // j台机器
            for (int j = M; j >= 0; j--) {

                for (int x = j; x >= 1; x--) {

                    // 收益
                    int value = w[i][x];

                    dp[i][j] = Math.max(
                            Math.max(dp[i + 1][j], dp[i][j]),
                            dp[i + 1][j - x] + value
                    );
                }

            }

        }

        // System.out.println("dp = " + Arrays.deepToString(dp));

        System.out.println(dp[0][M]);

        int X = M;

        for (int i = 0; i < N; i++) {

            for (int j = 0; j <= X; j++) {

                int value = w[i][j];

                if (dp[i + 1][X - j] + value == dp[i][X]) {
                    System.out.printf("%d %d %n", i + 1, j);
                    X -= j;
                    break;
                }

            }

        }

    }

}
