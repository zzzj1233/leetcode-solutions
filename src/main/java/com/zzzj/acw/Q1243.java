package com.zzzj.acw;

import java.util.Arrays;
import java.util.Scanner;

public class Q1243 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int M = scanner.nextInt();

        int K = scanner.nextInt();

        int limit = 1 << M;

        long[] dp = new long[limit];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 0; i < N; i++) {

            int stat = 0;

            for (int j = 0; j < K; j++)
                stat |= 1 << (scanner.nextInt() - 1);

            dp[stat] = 1;

            for (int s = 0; s < limit; s++) {

                dp[s] = Math.min(
                        dp[s],
                        dp[(s ^ stat) & s] + 1
                );

            }

        }

        if (dp[limit - 1] == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(dp[limit - 1]);
    }

}
