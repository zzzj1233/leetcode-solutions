package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2022-11-01 18:29
 */
public class Leet1328 {

    public static String breakPalindrome(String palindrome) {
        // "aba" ->
        // aab , abb
        int N = palindrome.length();

        if (N == 1) {
            return "";
        }

        char[] chars = palindrome.toCharArray();

        for (int i = 0; i < N; i++) {
            if (chars[i] != 'a') {
                chars[i] = 'a';
                break;
            }
        }

        for (int i = 0; i < N; i++) {
            if (chars[i] != 'a') {
                return String.valueOf(chars);
            }
        }

        StringBuilder builder = new StringBuilder(palindrome);

        builder.setCharAt(N - 1, 'b');

        return builder.toString();
    }

}
