package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-11-03 14:54
 */
public class Leet115 {

    public static void main(String[] args) {

        System.out.println(numDistinct("rabbbit", "rabbit"));

        System.out.println(numDistinct("babgbag", "bag"));

    }

    public static int numDistinct(String s, String t) {

        int N = s.length();

        int M = t.length();

        int[][] dp = new int[N + 1][M + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= N; i++) {

            for (int j = 1; j <= M; j++) {

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = Math.max(
                            dp[i - 1][j - 1],
                            dp[i - 1][j]
                    ) + 1;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }

        }

        return dp[N][M];
    }

}
