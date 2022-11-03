package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2022-11-01 18:29
 */
public class Leet1328 {

    public static String breakPalindrome(String palindrome) {

        int N = palindrome.length();

        if (N == 1) {
            return "";
        }

        StringBuilder builder = new StringBuilder(palindrome);

        for (int i = 0; i < N; i++) {
            char c = builder.charAt(i);
            if (c == 'a') {
                continue;
            }
            builder.setCharAt(i, (char) (c + 1));
            return builder.toString();
        }

        return "";
    }

}
