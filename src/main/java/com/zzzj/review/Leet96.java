package com.zzzj.review;

/**
 * @author zzzj
 * @create 2023-03-15 18:24
 */
public class Leet96 {

    public static void main(String[] args) {
        System.out.println(numTrees(5));
    }

    public static int numTrees(int n) {

        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {

            int result = 0;

            for (int j = 1; j <= i; j++) {
                result += dp[j - 1] * dp[i - j];
            }

            dp[i] = result;
        }

        return dp[n];
    }

}
