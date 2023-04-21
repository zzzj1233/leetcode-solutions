package com.zzzj.review;

/**
 * @author zzzj
 * @create 2023-03-07 18:29
 */
public class Leet5 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("aacabdkacaa"));
    }

    public static String longestPalindrome(String s) {

        // 最长回文字串

        int N = s.length();

        if (N == 0) {
            return "";
        }

        boolean[][] dp = new boolean[N][N];

        int max = 0;

        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }

        String ans = String.valueOf(s.charAt(0));

        for (int i = 0; i < N; i++) {

            char c = s.charAt(i);

            for (int j = 0; j < i; j++) {

                if (c != s.charAt(j)) {
                    continue;
                }

                if (i - j == 1) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = dp[i - 1][j + 1];
                }

                if (dp[i][j]) {
                    int len = i - j + 1;
                    if (len > ans.length()) {
                        ans = s.substring(j, i + 1);
                    }
                }

            }

        }

        return ans;
    }

}
