package com.zzzj.dp;

/**
 * @author Zzzj
 * @create 2022-07-12 18:36
 */
public class Leet1062 {

    public static void main(String[] args) {
        System.out.println(longestRepeatingSubstring("aaaaa"));
    }
    public static int longestRepeatingSubstring(String s) {


        int N = s.length();

        int[][] dp = new int[N][N];

        // 当s.charAt(i) == s.charAt(j)时 , dp[i][j] = dp[i-1][j-1] + 1

        // abcsabc

        int ans = 0;

        for (int i = 0; i < N; i++) {

            for (int j = i + 1; j < N; j++) {

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = (i >= 1 ? dp[i - 1][j - 1] : 0) + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }

        }

        return ans;
    }


}
