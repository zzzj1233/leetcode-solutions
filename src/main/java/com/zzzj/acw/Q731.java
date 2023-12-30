package com.zzzj.acw;

import java.util.Arrays;
import java.util.Scanner;

public class Q731 {


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

        dp[0][0] = 0;

        for (int stat = 0; stat < limit; stat++) {

            for (int last = 0; last < N; last++) {

                if ((stat & (1 << last)) == 0)
                    continue;

                for (int other = 0; other < N; other++) {

                    if (other == last)
                        continue;

                    int prevStat = stat - (1 << last);

                    dp[stat][last] = Math.min(
                            dp[stat][last],
                            dp[prevStat][other] + distance[other][last]
                    );

                }

            }

        }

        System.out.println(dp[limit - 1][0]);
    }

}
