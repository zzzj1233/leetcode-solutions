package com.zzzj.dp;


import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-11-02 10:40
 */
public class Leet1143 {

    public static void main(String[] args) {
        // "bsbininm"
        // "jmjkbkjkv"
        // System.out.println(dynamicPlanning("abcde", "zace"));
        System.out.println(longestCommonSubsequence("abcde", "ace"));
    }

    public static int longestCommonSubsequence(String text1, String text2) {

        int N = text1.length();
        int M = text2.length();

        int[][] dp = new int[N][M];

        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;

        for (int i = 1; i < M; i++) {
            if (text1.charAt(0) == text2.charAt(i)) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }

        for (int i = 1; i < N; i++) {
            if (text2.charAt(0) == text1.charAt(i)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }

        for (int i = 1; i < N; i++) {

            for (int j = 1; j < M; j++) {

                dp[i][j] = Math.max(dp[i - 1][j], Math.max(dp[i][j - 1], (text1.charAt(i) == text2.charAt(j) ? 1 : 0) + dp[i - 1][j - 1]));

            }

        }

        return dp[N - 1][M - 1];
    }

}
