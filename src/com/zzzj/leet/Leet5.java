package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-25 14:45
 */
public class Leet5 {

    public static void main(String[] args) {
        // dbbd
        System.out.println(longestPalindrome("dbbd"));
    }

    // babad = aba

    public static String longestPalindrome(String s) {
        int n = s.length();

        if (n < 2) {
            return s;
        }

        boolean[][] dp = new boolean[n][n];

        char[] chars = s.toCharArray();

        int left = 0;
        int right = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if ((i - j < 2 || dp[i - 1][j + 1]) && chars[i] == chars[j]) {
                    dp[i][j] = true;
                    if (i - j > right - left) {
                        left = j;
                        right = i;
                    }
                }
            }
        }

        if (left == 0 && right == 0) {
            return null;
        }
        return s.substring(left, right + 1);
    }

}
