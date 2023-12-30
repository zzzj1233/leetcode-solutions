package com.zzzj.acw;

import java.util.Scanner;

public class Q3494 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner("3 20 12");

        int MOD = 1000000007;

        int M = scanner.nextInt();

        int N = scanner.nextInt();

        int K = scanner.nextInt();

        int limit = 1 << M;

        int[][][][] dp = new int[N + 1][limit][limit][K + 1];

        dp[0][0][0][0] = 1;

        for (int i = 1; i <= N; i++) {

            for (int stat = 0; stat < limit; stat++) {

                int cnt = Integer.bitCount(stat);

                for (int prevStat = 0; prevStat < limit; prevStat++) {

                    // 检查是否可以攻击到
                    if ((prevStat & (stat << 2)) != 0 || (stat & (prevStat << 2)) != 0)
                        continue;

                    for (int pp = 0; pp < limit; pp++) {

                        // 检查是否可以攻击到
                        if ((pp & (stat << 1)) != 0 || (stat & (pp << 1)) != 0)
                            continue;

                        for (int k = cnt; k <= K; k++) {
                            dp[i][stat][prevStat][k] = (dp[i][stat][prevStat][k] + dp[i - 1][prevStat][pp][k - cnt]) % MOD;
                        }

                    }

                }

            }

        }

        int sum = 0;

        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < limit; j++) {
                sum = (sum + dp[N][i][j][K]) % MOD;
            }
        }

        System.out.println(sum);
    }

    private static boolean check(int stat, int bit) {
        if (bit < 0 || bit > 31)
            return false;
        return (stat & (1 << bit)) != 0;
    }

}
