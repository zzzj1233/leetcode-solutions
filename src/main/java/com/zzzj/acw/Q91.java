package com.zzzj.acw;

import java.util.Arrays;
import java.util.Scanner;

public class Q91 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[][] distance = new int[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                distance[i][j] = scanner.nextInt();

        int limit = 1 << N;

        long[][] dp = new long[limit][N];

        for (int i = 0; i < limit; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        dp[1][0] = 0;

        for (int stat = 0; stat < limit; stat++) {

            for (int end = 0; end < N; end++) {

                if ((stat & (1 << end)) == 0)
                    continue;

                for (int other = 0; other < N; other++) {

                    int prevStat = stat - (1 << end);

                    if ((prevStat & (1 << other)) == 0)
                        continue;

                    dp[stat][end] = Math.min(
                            dp[stat][end],
                            dp[prevStat][other] + distance[other][end]
                    );

                }

            }

        }

        System.out.println(dp[limit - 1][N - 1]);
    }

}
