package com.zzzj.acw;

import java.util.Scanner;

public class Q451 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int M = scanner.nextInt();

        final int MOD = 1000000007;

        int[] limit = new int[N];

        for (int i = 0; i < N; i++)
            limit[i] = scanner.nextInt();

        int[][] dp = new int[N + 1][M + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= N; i++) {

            for (int j = 0; j <= limit[i - 1]; j++) {

                for (int k = M; k >= j; k--) {
                    dp[i][k] = (dp[i][k] + dp[i - 1][k - j]) % MOD;
                }

            }

        }

        System.out.println(dp[N][M]);
    }

}
