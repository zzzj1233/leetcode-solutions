package com.zzzj.contest.dweek106;

/**
 * @author zzzj
 * @create 2023-07-27 11:07
 */
public class Leet2730 {

    public static void main(String[] args) {

        System.out.println(longestSemiRepetitiveSubstring("52233"));

        System.out.println(longestSemiRepetitiveSubstring("5494"));

        System.out.println(longestSemiRepetitiveSubstring("1111111"));
    }

    public static int longestSemiRepetitiveSubstring(String s) {

        int N = s.length();

        int left = 0;
        int right = 1;

        int ans = 1;

        boolean contains = false;

        while (right < N) {
            char c = s.charAt(right);

            if (c == s.charAt(right - 1)) {
                while (contains) {
                    if (s.charAt(left) == s.charAt(left + 1)) contains = false;
                    left++;
                }
                contains = true;
            }

            ans = Math.max(ans, right - left + 1);
            right++;
        }

        return ans;
    }

}
