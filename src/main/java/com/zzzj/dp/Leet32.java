package com.zzzj.dp;


import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-04-06 10:59
 */
public class Leet32 {

    public static void main(String[] args) {

//        System.out.println(longestValidParentheses("))())))"));
//
//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            String str = LeetUtils.randomString0("()", LeetUtils.random.nextInt(10) + 1);

            if (longestValidParentheses(str) != right(str)) {
                System.out.println(str);
                System.out.println(longestValidParentheses(str));
                System.out.println(i);
                return;
            }
        }
//        System.out.println(longestValidParentheses("(()"));
//        System.out.println(longestValidParentheses(")()())"));
    }

    public static int longestValidParentheses(String s) {
        return dp(s.toCharArray());
    }

    public static int dp(char[] str) {
        int N = str.length;

        int[] left = new int[str.length];

        left[0] = str[0] == '(' ? 1 : 0;

        for (int i = 1; i < N; i++) {
            if (str[i] == '(') {
                left[i] = Math.max(0, left[i - 1]) + 1;
            } else {
                left[i] = left[i - 1] - 1;
            }
        }

        left[0] = str[0] == '(' ? 1 : -1;

        int[] dp = new int[str.length + 1];

        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] == ')') {
                if (left[i] < 0) {
                    dp[i] = dp[i + 1];
                } else {
                    dp[i] = dp[i + 1] + 2;
                }
            } else {
                dp[i] = dp[i + 1];
            }
        }

        return dp[0];
    }

    public static int dfs(char[] str, int si, int[] left) {
        if (si == str.length) {
            return 0;
        }
        if (str[si] == '(') {
            return dfs(str, si + 1, left);
        } else {
            if (left[si] < 0) {
                return dfs(str, si + 1, left);
            }
            return dfs(str, si + 1, left) + 2;
        }
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
