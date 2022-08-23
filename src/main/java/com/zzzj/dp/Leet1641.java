package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-08-22 17:02
 */
public class Leet1641 {

    public static void main(String[] args) {
        System.out.println(countVowelStrings(2));
    }

    public static int countVowelStrings(int n) {
        // a, e, i, o, u
        int[][] dp = new int[26][n + 1];

        int a = 0;
        int e = 'e' - 'a';
        int i = 'i' - 'a';
        int o = 'o' - 'a';
        int u = 'u' - 'a';

        dp[a][1] = 1;
        dp[e][1] = 1;
        dp[i][1] = 1;
        dp[o][1] = 1;
        dp[u][1] = 1;

        for (int k = 2; k <= n; k++) {
            dp[a][k] = dp[a][k - 1];
            dp[e][k] = dp[e][k - 1] + dp[a][k - 1];
            dp[i][k] = dp[i][k - 1] + dp[e][k - 1] + dp[a][k - 1];
            dp[o][k] = dp[o][k - 1] + dp[i][k - 1] + dp[e][k - 1] + dp[a][k - 1];
            dp[u][k] = dp[u][k - 1] + dp[o][k - 1] + dp[i][k - 1] + dp[e][k - 1] + dp[a][k - 1];
        }

        return dp[a][n] + dp[e][n] + dp[i][n] + dp[o][n] + dp[u][n];
    }

}
