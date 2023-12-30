package com.zzzj.acw;

import java.util.Scanner;

public class Q1163 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 天数
        int T = scanner.nextInt();

        // 纪念品数量
        int N = scanner.nextInt();

        // 初始金币数
        int M = scanner.nextInt();

        // 第i天第j个纪念品的价格
        int[][] w = new int[T][N];

        for (int i = 0; i < T; i++)
            for (int j = 0; j < N; j++)
                w[i][j] = scanner.nextInt();


        int gold = M;

        for (int i = 1; i < T; i++) {

            int[] dp = new int[gold + 1];

            for (int j = 0; j < dp.length; j++) {
                dp[j] = j;
            }

            for (int j = 0; j < N; j++) {

                if (w[i][j] > w[i - 1][j]) {

                    for (int k = w[i - 1][j]; k < dp.length; k++) {

                        dp[k] = Math.max(
                                dp[k],
                                dp[k - w[i - 1][j]] + w[i][j]
                        );

                    }

                }

            }

            gold = dp[gold];
        }

        System.out.println(gold);
    }

}
