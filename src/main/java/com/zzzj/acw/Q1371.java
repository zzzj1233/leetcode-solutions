package com.zzzj.acw;

import java.util.Scanner;

public class Q1371 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int V = scanner.nextInt();

        int[] coin = new int[N];

        for (int i = 0; i < N; i++)
            coin[i] = scanner.nextInt();

        long[] dp = new long[V + 1];

        dp[0] = 1;

        for (int i = 0; i < N; i++) {

            int c = coin[i];

            for (int j = c; j <= V; j++) {
                dp[j] += dp[j - c];
            }

        }

        System.out.println(dp[V]);
    }

}
