package com.zzzj.acw;

import java.util.Arrays;
import java.util.Scanner;

public class Q1067 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int K = scanner.nextInt();

        int limit = 1 << N;

        int[][][] dp = new int[N + 1][K + 1][limit];

        dp[0][0][0] = 1;

        for (int i = 1; i <= N; i++) {

            for (int j = 0; j <= K; j++) {

                // 枚举状态
                for (int stat = 0; stat < limit; stat++) {

                    if (hasAdjacentOnes(stat) || Integer.bitCount(stat) > j) continue;

                    int cnt = Integer.bitCount(stat);

                    for (int prev = 0; prev < limit; prev++) {

                        if ((prev & stat) != 0 || hasAdjacentOnes(prev | stat)) continue;

                        dp[i][j][stat] += dp[i - 1][j - cnt][prev];
                    }

                }

            }

        }

        System.out.println(Arrays.stream(dp[N][K]).sum());

    }

    public static boolean hasAdjacentOnes(int n) {
        // 使用位运算检查是否有两个相邻的1
        return ((n & (n << 1)) != 0);
    }

}
