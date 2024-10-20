package com.zzzj.contest.week419;


/**
 * @author zzzj
 * @create 2024-10-15 22:40
 */
public class Leet3320 {

    public static void main(String[] args) {

        System.out.println(countWinningSequences("EW"));

        System.out.println(countWinningSequences("FWEFW"));

    }

    public static int countWinningSequences(String s) {

        int N = s.length();

        // 1. Bob 的出招序列未知，但保证 Bob 不会在连续两个回合中召唤相同的生物

        // 2. 如果 s[i] == 'F'，Alice 召唤火龙。
        //    如果 s[i] == 'W'，Alice 召唤水蛇。
        //    如果 s[i] == 'E'，Alice 召唤地精。

        // 3.  F > E
        //     W > F
        //     E > W

        // 0 > 2
        // 1 > 0
        // 2 > 1

        // 4. 果在 n 轮后 Bob 获得的总分 严格大于 Alice 的总分，则 Bob 战胜 Alice
        // 5. Bob 可以用来战胜 Alice 的不同出招序列的数量。

        final int MOD = 1000000007;

        int SCORE_ZERO = N + 1;

        int maxScore = (N << 1) + 1;

        long[][][] f = new long[N + 1][maxScore + 2][3];

        // N + 1 = 0分

        char alice = s.charAt(0);

        // F < W < E ( < F )
        if (alice == 'F') {
            f[1][SCORE_ZERO][0] = 1;
            f[1][SCORE_ZERO + 1][1] = 1;
            f[1][SCORE_ZERO - 1][2] = 1;
        } else if (alice == 'W') {
            f[1][SCORE_ZERO - 1][0] = 1;
            f[1][SCORE_ZERO][1] = 1;
            f[1][SCORE_ZERO + 1][2] = 1;
        } else {
            f[1][SCORE_ZERO + 1][0] = 1;
            f[1][SCORE_ZERO - 1][1] = 1;
            f[1][SCORE_ZERO][2] = 1;
        }

        for (int i = 2; i <= N; i++) {

            alice = s.charAt(i - 1);

            // alice = 0
            // 0 - 0 = 0
            // 1 > 0 = 1
            // 2 - 0 = 0
            long[][] p = f[i - 1];

            long[][] c = f[i];

            // WFW
            // FWF
            // WEW

            // 1 = -N
            // N + 1 = 0
            // (N << 1) + 1 = N
            for (int score = 1; score <= maxScore; score++) {

                // alice = 0
                // 0 - 0 = 0
                // 1 > 0 = 1
                // 2 - 0 = -1
                if (alice == 'F') {
                    c[score][0] = (p[score][1] + p[score][2]) % MOD;
                    c[score][1] = (p[score - 1][0] + p[score - 1][2]) % MOD;
                    c[score][2] = (p[score + 1][0] + p[score + 1][1]) % MOD;
                } else if (alice == 'W') {
                    // alice = 1
                    // 0 < 1 = -1
                    // 1 = 1 = 0
                    // 2 > 1 = 1
                    c[score][0] = (p[score + 1][1] + p[score + 1][2]) % MOD;
                    c[score][1] = (p[score][0] + p[score][2]) % MOD;
                    c[score][2] = (p[score - 1][1] + p[score - 1][0]) % MOD;
                } else {
                    // alice = 2
                    // 0 > 2 = -1
                    // 1 < 2 = 1
                    // 2 = 2 = 0
                    c[score][0] = (p[score - 1][1] + p[score - 1][2]) % MOD;
                    c[score][1] = (p[score + 1][0] + p[score + 1][2]) % MOD;
                    c[score][2] = (p[score][0] + p[score][1]) % MOD;
                }

            }

        }

        long ans = 0;

        for (int score = SCORE_ZERO + 1; score <= maxScore; score++)
            ans = (ans + f[N][score][0] + f[N][score][1] + f[N][score][2]) % MOD;

        return (int) ans;
    }

}