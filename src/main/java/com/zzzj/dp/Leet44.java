package com.zzzj.dp;


import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-04-03 18:17
 */
public class Leet44 {

    public static void main(String[] args) {

        // "b"
        // "?*?"
        System.out.println(isMatch("b", "?*?"));

        for (int i = 0; i < 10000; i++) {
            String s = LeetUtils.randomString(LeetUtils.random.nextInt(10) + 1, false);
            String p = LeetUtils.randomString(LeetUtils.random.nextInt(10) + 1, "abcdefghijklmnopqrstuvwxyz??????******");

            if (isMatch(s, p) != right(s, p)) {
                System.out.println("Error");
                return;
            }
        }

        System.out.println("OK");
    }


    public static boolean isAllWild(String p) {
        int N = p.length();

        for (int i = 0; i < N; i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }

        return true;
    }

    public static boolean isMatch(String s, String pattern) {
        if (s.isEmpty()) {
            return isAllWild(pattern);
        }

        if (pattern.isEmpty()) {
            return false;
        }

        int N = pattern.length();
        int M = s.length();

        boolean[][] dp = new boolean[N][M];

        char firstChar = pattern.charAt(0);

        if (firstChar == '?' || firstChar == s.charAt(0)) {
            dp[0][0] = true;
        } else if (firstChar == '*') {
            Arrays.fill(dp[0], true);
        }

        boolean isAllStar = true;

        firstChar = s.charAt(0);

        for (int i = 0; i < N; i++) {
            if (pattern.charAt(i) == '*') {
                dp[i][0] = true;
                continue;
            }
            if ((pattern.charAt(i) == '?' || pattern.charAt(i) == firstChar) && isAllStar) {
                isAllStar = false;
                dp[i][0] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i < N; i++) {

            for (int j = 1; j < M; j++) {

                char p = pattern.charAt(i);
                char c = s.charAt(j);

                if (p == '*') {
                    for (int k = 0; k <= j; k++) {
                        if (dp[i - 1][k]) {
                            dp[i][j] = true;
                            break;
                        }
                    }
                } else if (p == '?' || p == c) {
                    dp[i][j] = dp[i - 1][j - 1];
                }

            }

        }

        return dp[N - 1][M - 1];
    }

    public static boolean right(String str, String pattern) {
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


}
