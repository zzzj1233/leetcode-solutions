package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-11-06 11:48
 */
public class Leet1411 {

    public static void main(String[] args) {

        System.out.println(numOfWays(1));

        System.out.println(numOfWays(2));

        System.out.println(numOfWays(3));

        System.out.println(numOfWays(5000));

    }

    public static int numOfWays(int n) {

        final int mod = 1000000007;

        int[][][][] dp = new int[n][3][3][3];

        for (int g0 = 0; g0 < 3; g0++) {

            for (int g1 = 0; g1 < 3; g1++) {

                if (g1 == g0)
                    continue;

                for (int g2 = 0; g2 < 3; g2++) {

                    if (g2 == g1)
                        continue;

                    dp[0][g0][g1][g2] = 1;
                }

            }

        }

        for (int i = 1; i < n; i++) {

            for (int g0 = 0; g0 < 3; g0++) {

                for (int g1 = 0; g1 < 3; g1++) {

                    if (g1 == g0)
                        continue;

                    for (int g2 = 0; g2 < 3; g2++) {

                        if (g2 == g1)
                            continue;

                        for (int p0 = 0; p0 < 3; p0++) {
                            if (p0 == g0)
                                continue;
                            for (int p1 = 0; p1 < 3; p1++) {
                                if (p1 == g1)
                                    continue;
                                for (int p2 = 0; p2 < 3; p2++) {
                                    if (p2 == g2)
                                        continue;

                                    dp[i][g0][g1][g2] = (dp[i][g0][g1][g2] + dp[i - 1][p0][p1][p2]) % mod;
                                }
                            }
                        }
                    }

                }

            }
        }

        int ans = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    ans = (ans + dp[n - 1][i][j][k]) % mod;
                }
            }
        }

        return ans;
    }

}
