package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-04-06 09:56
 */
public class Leet10 {

    public static void main(String[] args) {

        System.out.println(isMatch("aa", "a"));

        System.exit(0);

        String strCandidate = "abcdefghijklmnopqrstuvwxyz";
        String patternCandidate = "abcdefghijklmnopqrstuvwxyz.*";
        for (int i = 0; i < 1000; i++) {
            String str = LeetUtils.randomString0(strCandidate, LeetUtils.random.nextInt(20) + 1);
            String pattern = LeetUtils.randomString0(patternCandidate, LeetUtils.random.nextInt(20) + 1);

            // 矫正pattern
            while (pattern.charAt(0) == '*') {
                pattern = LeetUtils.randomString0(patternCandidate, LeetUtils.random.nextInt(20) + 1);
            }

            if (isMatch(str, pattern) != right(str, pattern)) {
                System.out.println(str);
                System.out.println(pattern);
                System.out.println(isMatch(str, pattern));
            }
        }
    }

    public static boolean isMatch(String s, String p) {
        int N = s.length();
        int M = p.length();

        boolean[][] dp = new boolean[N][M];


        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                if (j > i) {
                    dp[i][j] = p.charAt(i) == '*';
                } else if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '*') {
                    dp[i][j] = true;
                } else {
                    
                }

            }

        }

        return dp[0][0];
    }

    public static boolean right(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

}
