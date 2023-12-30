package com.zzzj.acw;

import java.util.Scanner;

public class Q1377 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int M = scanner.nextInt();

        int N = scanner.nextInt();

        int[] dp = new int[M + 1];

        for (int i = 1; i <= N; i++) {

            int w = scanner.nextInt();

            int v = scanner.nextInt();

            for (int j = 1; j * v <= M; j++) {

                for (int k = M; k >= j * v; k--) {
                    dp[k] = Math.max(
                            dp[k],
                            dp[k - j * v] + j * w
                    );
                }

            }

        }

        System.out.println(dp[M]);
    }

}
