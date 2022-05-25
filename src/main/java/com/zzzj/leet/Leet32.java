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
        // ""
        System.out.println(longestValidParentheses("()(())"));
    }

    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int N = s.length();

        int[] dp = new int[N];

        int max = 0;

        char per = s.charAt(0);

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                // 左边是左括号
                if (per == '(') {
                    // ?() : 左边的左边必须是右
                    //
                    dp[i] = 2;
                    if (i - 2 >= 0 && s.charAt(i - 2) == ')') {
                        dp[i] += dp[i - 2];
                    }
                } else {
                    // ?)) chars[i - dp[i - 1]]必须是左
                    if (i - 2 >= 0 && i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = 2 + dp[i - 1];
                    }
                }

                max = Math.max(max, dp[i]);
            }
            per = c;
        }

        return max;
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
