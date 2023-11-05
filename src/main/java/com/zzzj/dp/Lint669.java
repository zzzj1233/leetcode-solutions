package com.zzzj.dp;

import java.util.Arrays;

public class Lint669 {

    public static void main(String[] args) {

        System.out.println(coinChange(new int[]{1, 2, 5}, 11));

        System.out.println(coinChange(new int[]{2}, 3));

        System.out.println(coinChange(new int[]{1, 9}, 0));

    }

    public static int coinChange(int[] coins, int amount) {

        int N = coins.length;

        int[] dp = new int[amount + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 0; i < N; i++) {

            int c = coins[i];

            for (int j = c; j <= amount; j++) {
                if (dp[j - c] == Integer.MAX_VALUE)
                    continue;
                dp[j] = Math.min(
                        dp[j],
                        dp[j - c] + 1
                );
            }

        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

}
