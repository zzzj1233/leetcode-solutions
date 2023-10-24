package com.zzzj.dp;


/**
 * @author zzzj
 * @create 2023-10-11 17:59
 */
public class Leet730 {

    public static void main(String[] args) {

        System.out.println(countPalindromicSubsequences("bccb"));

        System.out.println(countPalindromicSubsequences("abcdabcdab"));

        System.out.println(countPalindromicSubsequences("axbxcd"));

    }

    public static int countPalindromicSubsequences(String s) {

        int N = s.length();

        int[][] dp = new int[N + 1][N + 1];

//        for (int i = 1; i <= N; i++)
//            dp[i][i] = 1;

        for (int i = N; i > 0; i--) {

            for (int j = i + 1; j <= N; j++) {

                if (s.charAt(i - 1) == s.charAt(j - 1))
                    dp[i][j] = 1;

                dp[i][j] += dp[i][j - 1];
                dp[i][j] += dp[i + 1][j];
            }

        }

        return dp[1][N];
    }

}
