package com.zzzj.dp;

/**
 * @author Zzzj
 * @create 2022-07-12 15:53
 */
public class Leet97 {


    public static boolean isInterleave(String s1, String s2, String s3) {
        int N = s1.length();
        int M = s2.length();

        if (N + M != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[N + 1][M + 1];

        dp[0][0] = true;

        for (int i = 1; i <= M; i++) {
            if (s2.charAt(i - 1) != s3.charAt(i - 1)) {
                break;
            }
            dp[0][i] = true;
        }

        for (int i = 1; i <= N; i++) {
            if (s1.charAt(i - 1) != s3.charAt(i - 1)) {
                break;
            }
            dp[i][0] = true;
        }

        for (int i = 1; i <= N; i++) {

            for (int j = 1; j <= M; j++) {

                if ((s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j]) || (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1])) {
                    dp[i][j] = true;
                }

            }

        }

        return dp[N][M];
    }

}
