package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-08-18 16:59
 */
public class Leet2707 {

    public static void main(String[] args) {

        System.out.println(minExtraChar("leetscode", new String[]{"leet", "code", "leetcode"}));

        System.out.println(minExtraChar("sayhelloworld", new String[]{"hello", "world"}));


    }

    public static int minExtraChar(String s, String[] dictionary) {

        int N = s.length();

        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {

            // i作为最后一个字母
            dp[i] = dp[i - 1];

            OUTER:
            for (String s1 : dictionary) {

                if (i < s1.length()) continue;

                for (int j = s1.length() - 1, x = i - 1; j >= 0; j--, x--) {

                    if (s1.charAt(j) != s.charAt(x))
                        continue OUTER;
                }

                dp[i] = Math.max(
                        dp[i],
                        dp[i - s1.length()] + s1.length()
                );
            }

        }

        return s.length() - dp[N];
    }

}
