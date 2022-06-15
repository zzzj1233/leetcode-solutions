package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-06-15 17:01
 */
public class Leet518 {

    public static void main(String[] args) {
        System.out.println(change(125, new int[]{1, 2, 5}));
    }

    public static int change(int amount, int[] coins) {
        return dp(amount, coins);
    }

    public static int dp(int amount, int[] coins) {
        int N = coins.length;

        int[][] dp = new int[N][amount + 1];

        for (int i = 0; i < N; i++) {
            dp[i][amount] = 1;
        }

        for (int i = N - 1; i >= 0; i--) {

            for (int j = amount; j >= 0; j--) {

                for (int k = N - 1; k >= i; k--) {
                    int cur = j + coins[k];
                    dp[i][j] += cur > amount ? 0 : dp[k][cur];
                }

            }

        }

        return dp[0][0];
    }

    // 有N种货币
    // 有多少种凑出amount的可能
    public static int dfs(int amount, int[] coins, int cur, int i) {
        if (cur == amount) {
            return 1;
        }

        if (cur > amount) {
            return 0;
        }

        int res = 0;

        for (; i < coins.length; i++) {
            res += dfs(amount, coins, cur + coins[i], i);
        }

        return res;
    }

}
