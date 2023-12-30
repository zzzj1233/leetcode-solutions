package com.zzzj.acw;

import java.util.Scanner;

public class Q1509 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int W = scanner.nextInt();

        int power = scanner.nextInt() + scanner.nextInt();

        int[][] w = new int[N][2];

        for (int i = 0; i < N; i++) {
            w[i][0] = scanner.nextInt();
            w[i][1] = scanner.nextInt();
        }

        int[] dp = new int[W + 1];

        for (int i = 0; i < N; i++) {

            if (w[i][0] > power)
                continue;

            for (int j = W; j >= w[i][1]; j--) {
                dp[j] = Math.max(
                        dp[j],
                        dp[j - w[i][1]] + 1
                );
            }

        }
        System.out.println(dp[W]);

    }

}
