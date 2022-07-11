package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-07-08 14:52
 */
public class Leet132 {

    public static void main(String[] args) {
//        System.out.println(minCut("bb"));
//        System.out.println(minCut("aab"));
        System.out.println(minCut("cdd"));
        System.out.println(dfs("cdd".toCharArray(), isP("cdd".toCharArray()), 0));
    }

    public static int minCut(String s) {
        char[] chars = s.toCharArray();
        return dp(chars, isP(chars));
    }

    public static boolean[][] isP(char[] chars) {
        // i ~ j 是否是回文
        int N = chars.length;
        boolean[][] dp = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }

        // 01 12 23 34
        // 02 13 24
        // 03 14
        // 04
        for (int k = 1; k < N; k++) {
            int i = 0;

            for (int j = k; j < N; j++, i++) {
                if (chars[j] == chars[i]) {
                    dp[i][j] = j - i < 2 || dp[i + 1][j - 1];
                }
            }
        }

        return dp;
    }

    public static int dp(char[] chars, boolean[][] isP) {
        int N = chars.length;

        int[] dp = new int[N + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[N] = 0;

        for (int i = N - 1; i >= 0; i--) {
            dp[i] = N - i - 1;
            for (int j = N - 1; j > i; j--) {
                if (isP[i][j]) {
                    dp[i] = Math.min(dp[i], 1 + dp[j + 1]);
                }
            }
        }

        return dp[0] - 1;
    }

    public static int dfs(char[] chars, boolean[][] isP, int i) {
        int result = chars.length - i - 1;
        for (int j = i; j < chars.length; j++) {
            if (isP[i][j]) {
                result = Math.min(result, 1 + dfs(chars, isP, j + 1));
            }
        }
        return result;
    }

}
