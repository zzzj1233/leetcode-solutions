package com.zzzj.acw;

import java.util.Scanner;

public class Q11 {

    public static void main(String[] args) {

        final int MOD = 1000000007;

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int V = scanner.nextInt();

        int[][] W = new int[N][2];

        for (int i = 0; i < N; i++) {
            W[i][0] = scanner.nextInt();
            W[i][1] = scanner.nextInt();
        }

        int[] dp = new int[V + 1];

        int[] f = new int[V + 1];

        f[0] = 1;

        for (int i = 1; i <= N; i++) {

            int w = W[i - 1][0];
            int v = W[i - 1][1];

            for (int j = V; j >= w; j--) {

                if (dp[j] == dp[j - w] + v) {
                    f[j] = (f[j] + f[j - w]) % MOD;
                } else if (dp[j] < dp[j - w] + v) {
                    dp[j] = dp[j - w] + v;
                    f[j] = f[j - w];
                }
            }

        }

        int max = 0;

        for (int i = 0; i <= V; i++)
            max = Math.max(max, dp[i]);

        int ans = 0;

        for (int i = 0; i <= V; i++) {
            if (dp[i] == max)
                ans = (ans + f[i]) % MOD;
        }

        System.out.println(ans);
    }

}
