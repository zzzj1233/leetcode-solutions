package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-06-17 15:08
 */
public class Leet1155 {

    public static void main(String[] args) {
        System.out.println(numRollsToTarget(1, 6, 3));
        System.out.println(numRollsToTarget(2, 6, 7));
        System.out.println(numRollsToTarget(3, 8, 21));
        System.out.println(numRollsToTarget(30, 30, 200));
    }

    public static int numRollsToTarget(int n, int k, int target) {

        long[][] f = new long[2][target + 1];

        f[0][0] = 1;

        final int MOD = 1000000007;

        for (int i = 1; i <= n; i++) {

            for (int v = target; v >= 0; v--) {

                f[i & 1][v] = 0;

                for (int x = k; x > 0; x--) {

                    if (v - x >= 0)
                        f[i & 1][v] = (f[i & 1][v] + f[(i - 1) & 1][v - x]) % MOD;

                }

            }

        }

        return (int) (f[n & 1][target] % MOD);
    }

}
