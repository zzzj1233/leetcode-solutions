package com.zzzj.str;

/**
 * @author zzzj
 * @create 2023-06-12 11:46
 */
public class Leet2734 {

    public static void main(String[] args) {

        System.out.println(smallestString("cbabc"));

        System.out.println(smallestString("acbbc"));

        System.out.println(smallestString("leetcode"));

        System.out.println(smallestString("a"));

        System.out.println(smallestString("aa"));

    }

    public static String smallestString(String s) {
        int N = s.length();

        int start = 0;

        while (start < N - 1 && s.charAt(start) == 'a') start++;

        int end = start + 1;

        while (end < N && s.charAt(end) != 'a') end++;

        StringBuilder builder = new StringBuilder(N);

        for (int i = 0; i < start; i++) {
            builder.append(s.charAt(i));
        }

        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if (c == 'a') builder.append('z');
            else builder.append((char) (c - 1));
        }

        for (int i = end; i < N; i++) {
            builder.append(s.charAt(i));
        }

        return builder.toString();
    }

}
