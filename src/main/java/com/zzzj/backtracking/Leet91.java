package com.zzzj.backtracking;


/**
 * @author zzzj
 * @create 2022-04-08 11:00
 */
public class Leet91 {

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
        System.out.println(numDecodings("22601"));
        System.out.println(numDecodings("0"));
    }

    public static int numDecodings(String s) {
        return dp(s);
    }

    public static int dp(String s) {
        int N = s.length();

        char[] chars = s.toCharArray();

        int[] dp = new int[N + 1];

        dp[N] = 1;

        for (int i = N - 1; i >= 0; i--) {
            final char c = chars[i];
            if (c == '0') {
                dp[i] = 0;
            } else {
                int ways1 = 0;
                if (i + 1 < chars.length) {
                    if ((((int) c - '0') * 10) + ((int) chars[i + 1] - '0') <= 26) {
                        ways1 = dp[i + 2];
                    }
                }
                dp[i] = dp[i + 1] + ways1;
            }
        }

        return dp[0];
    }

    public static int dfs(char[] chars, int index) {
        if (index == chars.length) {
            return 1;
        }

        if (chars[index] == '0') {
            return 0;
        }

        char c = chars[index];

        int ways1 = 0;

        if (index + 1 < chars.length) {
            if ((((int) c - '0') * 10) + ((int) chars[index + 1] - '0') <= 26) {
                ways1 = dfs(chars, index + 2);
            }
        }

        return dfs(chars, index + 1) + ways1;
    }


}
