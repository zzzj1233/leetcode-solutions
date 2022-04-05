package com.zzzj.dp;

/**
 * @author Zzzj
 * @create 2022-04-03 18:17
 */
public class Leet44 {

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "*"));
        System.out.println(isMatch("cb", "?a"));
        System.out.println(isMatch("adceb", "*a*b"));
        System.out.println(isMatch("acdcb", "a*c?b"));
    }

    public static boolean isMatch(String str, String pattern) {
        int N = str.length();
        int M = pattern.length();

        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();

        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[N][M] = true;

        for (int m = M - 1; m >= 0; m--) {
            dp[N][m] = p[m] == '*' && dp[N][m + 1];
        }

        for (int i = N - 1; i >= 0; i--) {

            for (int j = M - 1; j >= 0; j--) {
                if (p[j] == '?') {
                    dp[i][j] = dp[i + 1][j + 1];
                } else if (p[j] == '*') {
                    for (int k = 0; k <= s.length - i; k++) {
                        if (dp[i + k][j + 1]) {
                            dp[i][j] = true;
                        }
                    }
                } else {
                    dp[i][j] = s[i] == p[j] && dp[i + 1][j + 1];
                }
            }

        }

        return dp[0][0];
    }


    public static boolean dfs(char[] s, char[] p, int si, int pi) {
        if (si == s.length) {
            if (pi == p.length) {
                return true;
            }
            return p[pi] == '*' && dfs(s, p, si, pi + 1);
        }

        if (pi == p.length) {
            return false;
        }

        if (p[pi] == '?') {
            return dfs(s, p, si + 1, pi + 1);
        } else if (p[pi] == '*') {
            for (int i = 0; i <= s.length - si; i++) {
                if (dfs(s, p, si + i, pi + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            return p[pi] == s[si] && dfs(s, p, si + 1, pi + 1);
        }
    }

}
