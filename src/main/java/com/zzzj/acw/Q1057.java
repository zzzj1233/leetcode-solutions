package com.zzzj.acw;

import java.util.Scanner;

public class Q1057 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int K = scanner.nextInt();

        int[] stock = new int[N];

        for (int i = 0; i < N; i++)
            stock[i] = scanner.nextInt();

        int[][] dp = new int[K + 1][2];

        for (int i = 0; i <= K; i++) {
            dp[i][0] = Integer.MIN_VALUE / 2;
            dp[i][1] = Integer.MIN_VALUE / 2;
        }

        dp[0][0] = 0;

        for (int i = 0; i < N; i++) {

            int end = Math.min(N, K);

            int price = stock[i];

            for (int j = 1; j <= end; j++) {

                dp[j][1] = Math.max(
                        dp[j][1],
                        dp[j - 1][0] - price
                );

                dp[j][0] = Math.max(
                        dp[j][0],
                        dp[j][1] + price
                );

            }

        }

        System.out.println(
                Math.max(
                        dp[K][0],
                        dp[K][1]
                )
        );

    }

}
