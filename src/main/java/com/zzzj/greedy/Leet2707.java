package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2023-06-08 11:57
 */
public class Leet2707 {

    public static void main(String[] args) {

        System.out.println(minExtraChar("leetscode", new String[]{"leet", "code", "leetcode"}));

        System.out.println(minExtraChar("sayhelloworld", new String[]{"hello", "world"}));

    }

    public static int minExtraChar(String s, String[] dictionary) {
        int N = s.length();

        int[] dp = new int[N + 1];

        dp[N] = 0;

        for (int x = N - 1; x >= 0; x--) {

            int min = Integer.MAX_VALUE;

            OUTER:
            for (String word : dictionary) {

                int len = s.length() - x;

                if (word.length() > len) {
                    continue;
                }

                int i = x;

                for (int j = 0; j < word.length(); i++, j++) {
                    if (s.charAt(i) != word.charAt(j)) continue OUTER;
                }

                // word可以被使用
                min = Math.min(min, dp[i]);
            }

            dp[x] = Math.min(min, dp[x + 1] + 1);
        }

        return dp[0];
    }

}
