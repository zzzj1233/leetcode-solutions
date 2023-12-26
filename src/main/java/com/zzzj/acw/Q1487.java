package com.zzzj.acw;

import java.util.Scanner;

/**
 * @author zzzj
 * @create 2023-12-04 17:51
 */
public class Q1487 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final long MOD = (long) (Math.pow(10, 9) + 7);

        int N = scanner.nextInt();

        int M = scanner.nextInt();

        int V = scanner.nextInt();

        long[] dp = new long[V + 1];

        dp[0] = 1;

        for (int i = 0; i < N; i++) {

            int v = scanner.nextInt();

            for (int j = v; j <= V; j++)
                dp[j] = (dp[j] + dp[j - v]) % MOD;

        }

        for (int i = 0; i < M; i++) {

            int v = scanner.nextInt();

            for (int j = V; j >= v; j--)
                dp[j] = (dp[j] + dp[j - v]) % MOD;

        }

        System.out.println(dp[V]);
    }

}
