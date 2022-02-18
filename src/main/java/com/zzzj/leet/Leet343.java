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

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 1; i <= n; i++) {
            int max = 1;
            for (int j = 1; j < i; j++) {
                max = Math.max(Math.max(max, j * (i - j)), dp[j] * (i - j));
            }
            dp[i] = max;
        }

        return dp[n];
    }

}
