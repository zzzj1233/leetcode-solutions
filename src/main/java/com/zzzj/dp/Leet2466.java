package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-05-24 15:41
 */
public class Leet2466 {

    public static void main(String[] args) {

        System.out.println(countGoodStrings(3, 3, 1, 1));

        System.out.println(countGoodStrings(2, 3, 1, 2));

    }

    public static int countGoodStrings(int low, int high, int zero, int one) {
        int[] dp = new int[high + 1];

        dp[high] = 1;

        final int MOD = 1000000007;

        for (int i = high - 1; i >= 0; i--) {
            int x = i + one;
            int y = i + zero;

            if (x <= high) dp[i] += dp[x] % MOD;
            if (y <= high) dp[i] += dp[y] % MOD;

            if (i >= low) dp[i]++;

            dp[i] %= MOD;
        }

        return dp[0];
    }

}
