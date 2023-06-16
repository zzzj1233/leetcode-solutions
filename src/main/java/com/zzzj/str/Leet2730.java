package com.zzzj.str;

/**
 * @author zzzj
 * @create 2023-06-12 10:38
 */
public class Leet2730 {

    public static void main(String[] args) {

        System.out.println(longestSemiRepetitiveSubstring("52233"));

        System.out.println(longestSemiRepetitiveSubstring("5494"));
//
        System.out.println(longestSemiRepetitiveSubstring("1111111"));

        System.out.println(longestSemiRepetitiveSubstring("0001"));

    }

    public static int longestSemiRepetitiveSubstring(String s) {

        int N = s.length();

        int left = 0;

        int right = 1;

        int lastOccurIndex = -1;

        int ans = 1;

        boolean occur = false;

        while (right < N) {

            char c = s.charAt(right);

            if (c == s.charAt(right - 1)) {

                if (occur) {
                    left = lastOccurIndex + 1;
                } else {
                    occur = true;
                }

                lastOccurIndex = right - 1;

            }
            ans = Math.max(ans, right - left + 1);

            right++;
        }

        return ans;
    }

}
