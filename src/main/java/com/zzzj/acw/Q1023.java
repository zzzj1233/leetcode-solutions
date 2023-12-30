package com.zzzj.acw;

import java.util.Scanner;

public class Q1023 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int M = 4;

        int[] w = {10, 20, 50, 100};

        int[] dp = new int[N + 1];

        dp[0] = 1;

        for (int i = 0; i < M; i++) {

            for (int sum = w[i]; sum < dp.length; sum++) {

                dp[sum] += dp[sum - w[i]];

            }

        }

        System.out.println(dp[N]);
    }

}
