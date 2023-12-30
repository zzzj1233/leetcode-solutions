package com.zzzj.acw;

import java.util.Scanner;

public class Q7 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner("4 5\n" +
                "1 2 -1\n" +
                "2 4 1\n" +
                "3 4 0\n" +
                "4 5 2");

        int N = scanner.nextInt();

        int V = scanner.nextInt();

        int[][] W = new int[N][3];

        for (int i = 0; i < N; i++) {
            W[i][0] = scanner.nextInt();
            W[i][1] = scanner.nextInt();
            W[i][2] = scanner.nextInt();
        }

        int[] dp = new int[V + 1];

        for (int i = 0; i < N; i++) {

            int[] w = W[i];

            // 01背包
            if (w[2] == -1) {
                for (int j = V; j >= w[0]; j--)
                    dp[j] = Math.max(
                            dp[j],
                            dp[j - w[0]] + w[1]
                    );
            } else if (w[2] == 0) { // 完全背包

                for (int j = V; j >= 0; j--) {

                    for (int k = 0; k * w[0] <= j; k++) {
                        dp[j] = Math.max(
                                dp[j],
                                dp[j - k * w[0]] + k * w[1]
                        );
                    }

                }

            } else { // 多重背包

                for (int j = V; j >= 0; j--) {

                    for (int k = 0; k <= w[2]; k++) {

                        int v = k * w[0];

                        if (j < v)
                            break;

                        dp[j] = Math.max(
                                dp[j],
                                dp[j - v] + k * w[1]
                        );

                    }

                }

            }

        }

        System.out.println(dp[V]);

    }

}
