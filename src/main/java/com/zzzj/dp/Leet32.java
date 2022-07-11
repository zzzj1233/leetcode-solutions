package com.zzzj.dp;

public class Leet32 {

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("()(()"));
    }

    public static int longestValidParentheses(String s) {

        int N = s.length();

        int[] dp = new int[N];

        int ans = 0;

        for (int i = 1; i < N; i++) {
            if (s.charAt(i) == '(') {
                continue;
            }
            // 左边是左括号
            if (s.charAt(i - 1) == '(') {
                dp[i] = 2 + (i - 2 >= 0 ? dp[i - 2] : 0);
                // 左边是右括号
            } else {
                if (i - dp[i - 1] - 1 >= 0) {
                    char c = s.charAt(i - dp[i - 1] - 1);
                    if (c == '(') {
                        dp[i] = 2 + dp[i - 1] + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                    }
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

}