package com.zzzj.acw;

import java.util.Scanner;

/**
 * @author zzzj
 * @create 2023-12-04 17:09
 */
public class Q3382 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final long mod = (long) Math.pow(10, 9);

        int N = scanner.nextInt();

        long[] dp = new long[N + 1];

        dp[0] = 1;

        for (int i = 0; i <= 20; i++) {

            int v = 1 << i;

            if (v > N) {
                break;
            }

            for (int j = v; j <= N; j++)
                dp[j] = (dp[j] + dp[j - v]) % mod;

        }

        System.out.println(dp[N]);
    }

}
