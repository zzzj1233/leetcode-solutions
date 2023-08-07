package com.zzzj.contest.dweek105;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-07-31 15:39
 */
public class Leet2707 {

    // ddcaaadada

    // aadad, dcccd, adcba, ddcaa, acddb
    public static void main(String[] args) {

        System.out.println(minExtraChar("sayhelloworld", new String[]{"hello", "world"}));

//        System.exit(0);

        for (int i = 0; i < 10000; i++) {
            String str = LeetUtils.randomString(30 );
            String[] dict = new String[LeetUtils.random.nextInt(str.length()) + 1];
            for (int j = 0; j < dict.length; j++) {
                dict[j] = LeetUtils.randomString(10 );
            }
            if (minExtraChar(str, dict) != right(str, dict)) {
                System.out.println("Error");
                System.out.println("str = " + str);
                System.out.println("dict = " + Arrays.toString(dict));
                System.out.println("minExtraChar(str,dict) = " + minExtraChar(str, dict));
                System.out.println("right(str,dict) = " + right(str, dict));
                break;
            }
        }

    }

    public static int minExtraChar(String s, String[] dictionary) {

        int N = s.length();

        int max = -1;

        int[] dp = new int[N + 1];

        for (int i = N - 1; i >= 0; i--) {

            dp[i] = dp[i + 1];

            OUTER:
            for (String s1 : dictionary) {

                if (s1.length() > N - i) continue;

                for (int j = 0, index = i; j < s1.length(); j++, index++) {

                    if (s1.charAt(j) != s.charAt(index)) {
                        continue OUTER;
                    }

                }

                dp[i] = Math.max(
                        dp[i],
                        s1.length() + dp[i + s1.length()]
                );
            }

        }

        return s.length() - dp[0];
    }


    public static int right(String s, String[] dictionary) {
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
