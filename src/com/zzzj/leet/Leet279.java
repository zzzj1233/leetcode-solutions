package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-20 17:37
 */
public class Leet279 {

    public static void main(String[] args) {
        System.out.println(numSquares(13));
    }

    // 12 = 4 + 4 + 4
    // 13 = 9 + 4
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int j = 1;
            int min = Integer.MAX_VALUE;
            for (; j * j <= i; j++) {
                int sub = i - j * j;
                min = Math.min(min, dp[sub] + dp[j * j]);
            }
            dp[i] = min;
        }

        return dp[n];
    }

}
