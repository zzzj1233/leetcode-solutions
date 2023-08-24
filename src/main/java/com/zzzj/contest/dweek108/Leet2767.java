package com.zzzj.contest.dweek108;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-07-17 17:55
 */
public class Leet2767 {

    public static void main(String[] args) {

        System.out.println(minimumBeautifulSubstrings("1011"));

        System.out.println(minimumBeautifulSubstrings("111"));

        System.out.println(minimumBeautifulSubstrings("0"));

    }

    public static String[] candidates = {
            "1",
            "101",
            "11001",
            "1111101",
            "1001110001",
            "110000110101",
            "11110100001001"
    };

    public static boolean isBeauty(String s, int left, int right) {
        int len = right - left + 1;

        for (String candidate : candidates) {
            if (candidate.length() == len) {

                for (int i = 0; i < len; i++) {
                    if (s.charAt(i + left) != candidate.charAt(i)) return false;
                }

                return true;
            }
        }

        return false;
    }

    public static int dp(String s) {
        int N = s.length();

        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);

        for (int i = N - 1; i >= 0; i--) {

            for (int j = N - 1; j >= i; j--) {

                if (isBeauty(s, i, j)) {
                    dp[i][j] = 1;
                    continue;
                }

                int min = Integer.MAX_VALUE;

                for (int left = i; left <= j; left++) {

                    if (isBeauty(s, i, left) && left + 1 < N && dp[left + 1][j] != Integer.MAX_VALUE) {
                        min = Math.min(min, dp[left + 1][j] + 1);
                    }

                }

                dp[i][j] = min;
            }

        }

        return dp[0][N - 1];
    }

    public static int minimumBeautifulSubstrings(String s) {
        if (s.charAt(0) == '0') return -1;

        int dp = dp(s);

        return dp == Integer.MAX_VALUE ? -1 : dp;
    }

}
