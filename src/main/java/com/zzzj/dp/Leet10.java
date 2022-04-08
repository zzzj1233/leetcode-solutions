package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-04-06 09:56
 */
public class Leet10 {

    public static void main(String[] args) {

        System.out.println(isMatch("aab", "c*a*b*"));


        System.exit(0);
        String strCandidate = "abcdefghijklmnopqrstuvwxyz";
        String patternCandidate = "abcdefghijklmnopqrstuvwxyz.*";
        for (int i = 0; i < 1000; i++) {
            String str = LeetUtils.randomString0(strCandidate, LeetUtils.random.nextInt(20) + 1);
            String pattern = LeetUtils.randomString0(patternCandidate, LeetUtils.random.nextInt(20) + 1);

            // 矫正pattern
            while (pattern.charAt(0) == '*') {
                pattern = LeetUtils.randomString0(patternCandidate, LeetUtils.random.nextInt(20) + 1);
                ;
            }

            if (isMatch(str, pattern) != right(str, pattern)) {
                System.out.println(str);
                System.out.println(pattern);
                System.out.println(isMatch(str, pattern));
            }
        }
    }

    public static boolean isMatch(String s, String p) {
        return dfs(s.toCharArray(), p.toCharArray(), 0, 0);
        // return dp(s, p);
    }


    public static boolean dp(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();

        dp[s.length()][p.length()] = true;

        for (int j = p.length() - 1; j >= 0; j--) {
            dp[s.length()][j] = pattern[j] == '*' && dp[s.length()][j + 1];
        }

        for (int i = s.length() - 1; i >= 0; i--) {

            for (int j = p.length() - 1; j >= 0; j--) {

                if (pattern[j] == '.') {
                    dp[i][j] = dp[i + 1][j + 1];
                } else if (pattern[j] == '*') {
                    // 判断上一个字符是啥
                    if (j - 1 < 0) {
                        dp[i][j] = false;
                        continue;
                    }

                    char prev = pattern[j - 1];

                    if (prev == str[i] || prev == '.') {
                        // 可以使用*也可以不使用*
                        dp[i][j] = dp[i + 1][j] || dp[i + 1][j + 1];
                    } else {
                        dp[i][j] = dp[i + 1][j + 1];
                    }
                } else {
                    dp[i][j] = pattern[j] == str[i] && dp[i + 1][j + 1];
                }
            }

        }

        return dp[0][0];
    }

    public static boolean dfs(char[] str, char[] pattern, int si, int pi) {
        // 字符串到末尾了
        if (si == str.length) {
            if (pi == pattern.length) {
                return true;
            }
            // 只允许是星
            return pattern[pi] == '*' && dfs(str, pattern, si, pi + 1);
        }

        if (pi == pattern.length) {
            return false;
        }

        if (pattern[pi] == '.') {
            return dfs(str, pattern, si + 1, pi + 1);
        }

        if (pattern[pi] == '*') {
            // 判断上一个字符是啥
            char prev = pattern[pi - 1];

            if (prev == str[si] || prev == '.') {
                // 可以使用*也可以不使用*
                return dfs(str, pattern, si + 1, pi) || dfs(str, pattern, si + 1, pi + 1);
            } else {
                return dfs(str, pattern, si + 1, pi + 1);
            }
        }

        // 字母匹配
        if (pattern[pi] == str[si]) {
            return dfs(str, pattern, si + 1, pi + 1);
        }
        return pi + 1 < pattern.length && pattern[pi + 1] == '*' && (dfs(str, pattern, si + 1, pi) || dfs(str, pattern, si + 1, pi + 1));
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
