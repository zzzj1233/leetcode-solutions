package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-11-03 15:37
 */
public class Leet516 {

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("bbbabccb"));
    }

    public static int longestPalindromeSubseq(String s) {
        int N = s.length();

        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }

        for (int i = N - 2; i >= 0; i--) {

            for (int j = i + 1; j < N; j++) {

                int way = s.charAt(i) == s.charAt(j) ? 2 : 0;

                dp[i][j] = Math.max(dp[i + 1][j], Math.max(dp[i][j - 1], dp[i + 1][j - 1] + way));
            }

        }

        return dp[0][N - 1];
    }


}
