package com.zzzj.acw;

import java.util.Scanner;

public class Q1058 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] stock = new int[N];

        for (int i = 0; i < N; i++)
            stock[i] = scanner.nextInt();

        int[] dp = new int[3];

        dp[1] = Integer.MIN_VALUE / 2;
        dp[2] = Integer.MIN_VALUE / 2;

        for (int i = 0; i < N; i++) {

            int price = stock[i];

            // 未持有可交易
            int c1 = dp[1];
            // 未持有不可交易
            int c2 = dp[2] + price;
            // 持有
            int c3 = dp[0] - price;

            dp[0] = Math.max(dp[0], c1);
            dp[1] = Math.max(dp[1], c2);
            dp[2] = Math.max(dp[2], c3);
        }

        System.out.println(Math.max(
                Math.max(dp[1], dp[0]),
                0
        ));
    }

}
