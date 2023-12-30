package com.zzzj.acw;

import java.util.Arrays;
import java.util.Scanner;

public class Q327 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner("2 3\n" +
                "1 1 1\n" +
                "0 1 0");

        int M = scanner.nextInt();

        int N = scanner.nextInt();

        int[] corn = new int[M];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (scanner.nextInt() == 0) {
                    corn[i] |= 1 << j;
                }
            }
        }

        final int MOD = (int) Math.pow(10, 8);

        // 0表示不能种植

        int limit = 1 << N;

        long[][] dp = new long[M + 1][limit];

        dp[0][0] = 1;

        for (int i = 1; i <= M; i++) {

            for (int stat = 0; stat < limit; stat++) {

                if ((stat & corn[i - 1]) != 0 || hasAdjacentOnes(stat)) continue;

                for (int prev = 0; prev < limit; prev++) {

                    if ((prev & stat) != 0) continue;

                    dp[i][stat] = (dp[i][stat] + dp[i - 1][prev]) % MOD;

                }

            }

        }

        System.out.println(Arrays.stream(dp[M]).sum() % MOD);
    }

    public static boolean hasAdjacentOnes(int n) {
        // 使用位运算检查是否有两个相邻的1
        return ((n & (n << 1)) != 0);
    }

}
