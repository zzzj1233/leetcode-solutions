package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-11-01 15:27
 */
public class Leet647 {

    public static void main(String[] args) {
        String str = "aaaaa";
        System.out.println(countSubstrings(str));
    }

    public static int countSubstrings(String s) {
        int N = s.length();

        boolean[][] dp = new boolean[N][N];

        int ans = N;

        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }

        // 01,02,03,04
        // 12,13,14
        // 23,24
        // 34

        for (int i = N - 1; i >= 0; i--) {

            for (int j = i + 1; j < N; j++) {

                if (s.charAt(j) == s.charAt(i)) {
                    if (j - i < 2) {
                        dp[i][j] = true;
                        ans++;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                        if (dp[i][j]) {
                            ans++;
                        }
                    }
                }

            }

        }

        return ans;
    }

}
