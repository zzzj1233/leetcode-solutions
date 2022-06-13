package com.zzzj.leet;


import com.zzzj.util.Unresolved;

/**
 * @author zzzj
 * @create 2022-05-24 18:34
 */
@Unresolved
public class Leet32 {

    public static void main(String[] args) {
//        System.out.println(longestValidParentheses("(()"));
//        System.out.println(longestValidParentheses(")()())"));
//        System.out.println(longestValidParentheses("()(())"));
        for (int i = 0; i < 10000; i++) {
            final String str = LeetUtils.randomString0("()", 100);
            if (longestValidParentheses(str) != right(str)) {
                System.out.println(str);
                System.out.println(longestValidParentheses(str));
                return;
            }
        }
    }

    public static int longestValidParentheses(String s) {
        if (s.length() < 2) {
            return 0;
        }

        int[] dp = new int[s.length()];
        int ans = 0;

        dp[0] = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != ')') {
                continue;
            }
            char per = s.charAt(i - 1);

            // 如果左边是左括号
            if (per == '(') {
                dp[i] = 2 + (i - 2 >= 0 ? dp[i - 2] : 0);
            } else { // 如果左边是右括号
                int preAns = dp[i - 1];
                if (i - preAns - 1 >= 0 && s.charAt(i - preAns - 1) == '(') {
                    dp[i] = 2 + preAns;
                    if (i - preAns - 2 >= 0) {
                        dp[i] += dp[i - preAns - 2];
                    }
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }


    public static int right(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }


}
