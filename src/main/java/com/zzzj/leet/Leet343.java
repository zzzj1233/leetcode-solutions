package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-26 11:08
 */
public class Leet343 {

    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }

    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {

            int max = 0;

            for (int j = 1; j <= i; j++) {
                max = Math.max(max, Math.max(dp[j] * (i - j), j * (i - j)));
            }

            dp[i] = max;
        }

        return dp[n];
    }

}
