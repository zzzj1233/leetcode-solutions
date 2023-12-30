package com.zzzj.acw;

import java.util.Scanner;

public class Q1019 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int sum = scanner.nextInt();

        int[][] w = new int[N][3];

        for (int i = 0; i < N; i++) {
            w[i][0] = scanner.nextInt();
            w[i][1] = scanner.nextInt();
            w[i][2] = scanner.nextInt();
        }

        int[] dp = new int[sum + 1];

        for (int i = 0; i < N; i++) {

            for (int x = sum; x >= 0; x--) {

                for (int times = 0; times <= w[i][2]; times++) {

                    int limit = times * w[i][0];

                    if (x - limit < 0)
                        break;

                    int p = w[i][1] * times;

                    dp[x] = Math.max(
                            dp[x],
                            dp[x - limit] + p
                    );

                }

            }

        }

        System.out.println(dp[sum]);
    }

}
