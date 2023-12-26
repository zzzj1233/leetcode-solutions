package com.zzzj.acw;

import java.util.Scanner;

/**
 * @author zzzj
 * @create 2023-12-04 17:18
 */
public class Q4877 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int V = scanner.nextInt();

        int N = scanner.nextInt();

        int unlimitedV = scanner.nextInt();

        int unlimitedW = scanner.nextInt();

        int[][] W = new int[N][3];

        for (int i = 0; i < N; i++) {
            int cnt = scanner.nextInt() / scanner.nextInt();
            W[i][0] = scanner.nextInt();
            W[i][1] = scanner.nextInt();
            W[i][2] = cnt;
        }

        int[][] dp = new int[N + 1][V + 1];

        for (int i = unlimitedV; i <= V; i++)
            dp[0][i] = dp[0][i - unlimitedV] + unlimitedW;

        for (int i = 1; i <= N; i++) {

            int[] w = W[i - 1];

            for (int j = 0; j <= w[2] && j * w[0] <= V; j++) {

                for (int k = j * w[0]; k <= V; k++) {

                    dp[i][k] = Math.max(
                            Math.max(
                                    dp[i][k],
                                    dp[i - 1][k]
                            ),
                            dp[i - 1][k - j * w[0]] + j * w[1]
                    );

                }

            }

        }

        System.out.println(dp[N][V]);
    }

}
