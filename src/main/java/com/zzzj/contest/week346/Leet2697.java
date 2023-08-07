package com.zzzj.contest.week346;

/**
 * @author zzzj
 * @create 2023-08-01 12:03
 */
public class Leet2697 {

    public static void main(String[] args) {

        System.out.println(makeSmallestPalindrome("egcfe"));

        System.out.println(makeSmallestPalindrome("abcd"));

        System.out.println(makeSmallestPalindrome("seven"));

    }

    public static String makeSmallestPalindrome(String s) {

        int left = 0;

        int right = s.length() - 1;

        StringBuilder builder = new StringBuilder(s);

        while (left < right) {
            char L = builder.charAt(left);
            char R = builder.charAt(right);

            if (L < R) {
                builder.setCharAt(right, L);
            } else {
                builder.setCharAt(left, R);
            }
            left++;
            right--;
        }

        return builder.toString();
    }

}
