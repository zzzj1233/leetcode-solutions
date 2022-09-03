package com.zzzj.dp;


/**
 * @author zzzj
 * @create 2022-08-30 15:48
 */
public class Leet2697 {

    public static void main(String[] args) {
        System.out.println(waysToChange(5));
        System.out.println(waysToChange(20));
        System.out.println(waysToChange(40));
    }

    public static int waysToChange(int n) {
        // 25分,10分,5分,1分
        int[] coins = {1, 5, 10, 25};

        final int MOD = 1000000007;

        int[][] dp = new int[4][n + 1];

        for (int i = 0; i <= n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < 4; i++) {
            int coin = coins[i];

            if (coin > n) {
                dp[i] = dp[i - 1];
                continue;
            }

            for (int j = 0; j <= n; j++) {
                dp[i][j] = (dp[i - 1][j] + (j - coin >= 0 ? (dp[i][j - coin]) : 0)) % MOD;
            }

        }

        return dp[3][n] % MOD;
    }

}
