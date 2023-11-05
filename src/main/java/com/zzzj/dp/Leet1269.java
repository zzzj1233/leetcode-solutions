package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-10-24 15:55
 */
public class Leet1269 {

    public static void main(String[] args) {

        System.out.println(numWays(4, 2));

    }

    public static int numWays(int steps, int arrLen) {

        final int MOD = 1000000007;

        int maxCol = Math.min(arrLen, steps + 1);

        long[] dp = new long[maxCol];

        dp[0] = 1;

        long[] roll = new long[maxCol];

        for (int i = 1; i <= steps; i++) {

            for (int j = 0; j < maxCol; j++) {
                roll[j] = (dp[j] + (j - 1 >= 0 ? dp[j - 1] : 0) + (j + 1 < maxCol ? dp[j + 1] : 0)) % MOD;
            }

            long[] temp = dp;
            dp = roll;
            roll = temp;
        }

        return (int) dp[0];
    }


}
