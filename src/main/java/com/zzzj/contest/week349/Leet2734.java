package com.zzzj.contest.week349;

/**
 * @author zzzj
 * @create 2023-07-25 17:32
 */
public class Leet2734 {

    public static void main(String[] args) {

        System.out.println(smallestString("cbabc"));

        System.out.println(smallestString("acbbc"));

        System.out.println(smallestString("leetcode"));

    }

    public static String smallestString(String s) {

        int N = s.length();

        StringBuilder builder = new StringBuilder(s);

        int start = 0;

        while (start < N && builder.charAt(start) == 'a') start++;

        if (start == N) {
            builder.setCharAt(builder.length() - 1, 'z');
            return builder.toString();
        }

        for (int i = start; i < N; i++) {

            if (builder.charAt(i) == 'a') {
                return builder.toString();
            }

            builder.setCharAt(i, (char) (builder.charAt(i) - 1));
        }

        return builder.toString();
    }

}
