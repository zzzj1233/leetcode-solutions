package com.zzzj.backtracking;

/**
 * @author zzzj
 * @create 2022-04-08 11:00
 */
public class Leet91 {

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
        System.out.println(numDecodings("226"));
        System.out.println(numDecodings("0"));
    }

    public static int numDecodings(String s) {
        return dp(s);
    }

    public static int dp(String s) {
        char[] chars = s.toCharArray();
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (chars[i] == '0') {
                continue;
            }
            int ways = dp[i + 1];
            if (i + 1 < chars.length) {
                if ((chars[i] - '0') * 10 + chars[i + 1] - '0' <= 26) {
                    ways += dp[i + 2];
                }
            }
            dp[i] = ways;
        }

        return dp[0];
    }

    public static int dfs(char[] chars, int i) {
        if (i == chars.length) {
            return 1;
        }

        if (chars[i] == '0') {
            return 0;
        }

        int ways = dfs(chars, i + 1);

        if (i + 1 < chars.length) {
            if ((chars[i] - '0') * 10 + chars[i + 1] - '0' <= 26) {
                return ways + dfs(chars, i + 2);
            }
        }

        return ways;
    }

}
