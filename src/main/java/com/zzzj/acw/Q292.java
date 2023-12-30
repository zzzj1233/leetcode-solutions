package com.zzzj.acw;

import java.util.Scanner;

public class Q292 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int M = scanner.nextInt();

        int[] grid = new int[N + 1];

        for (int i = 1; i <= N; i++) {

            String line = scanner.next();

            for (int j = 0; j < M; j++) {
                if (line.charAt(j) == 'H')
                    grid[i] |= 1 << j;
            }

        }

        int limit = 1 << M;

        int[][][] dp = new int[2][limit][limit];

        for (int i = 1; i <= N; i++) {

            for (int stat = 0; stat < limit; stat++) {

                if (!checkStat(stat) || (grid[i] & stat) != 0)
                    continue;

                int cnt = Integer.bitCount(stat);

                for (int prevStat = 0; prevStat < limit; prevStat++) {

                    if ((grid[i - 1] & prevStat) != 0)
                        continue;

                    for (int pp = 0; pp < limit; pp++) {

                        if ((stat & prevStat) != 0 || (stat & pp) != 0 || (prevStat & pp) != 0)
                            continue;


                        dp[i & 1][stat][prevStat] = Math.max(
                                dp[i & 1][stat][prevStat],
                                dp[(i - 1) & 1][prevStat][pp] + cnt
                        );
                    }

                }

            }

        }

        int max = 0;

        for (int[] item : dp[N & 1]) {
            for (int i : item) {
                max = Math.max(max, i);
            }
        }

        System.out.println(max);
    }

    private static boolean checkStat(int stat) {

        for (int i = 0; i < 32; i++) {

            if (check(stat, i)) {
                if (check(stat, i - 1) || check(stat, i - 2) || check(stat, i + 1) || check(stat, i + 2))
                    return false;
            }

        }

        return true;
    }

    private static boolean check(int stat, int bit) {
        if (bit < 0 || bit > 31)
            return false;
        return (stat & (1 << bit)) != 0;
    }

}
