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

        dp[2] = 1;

        for (int i = 3; i <= n; i++) {

        }

        return dp[n];
    }

}
