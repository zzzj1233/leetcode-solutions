package com.zzzj.str;

/**
 * @author zzzj
 * @create 2023-10-01 11:26
 */
public class Leet1392 {

    public static void main(String[] args) {

        System.out.println(longestPrefix("level"));

        System.out.println(longestPrefix("ababab"));

    }

    // KMP
    public static String longestPrefix(String s) {

        int N = s.length();

        if (N <= 2)
            return "";

        int x = next(s);

        if (x == 0)
            return "";

        return s.substring(0, x);
    }

    public static int next(String str) {

        int N = str.length();

        int[] next = new int[N + 1];

        next[0] = -1;
        next[1] = 0;

        int index = 2;
        int cc = 0;

        while (index <= N) {
            if (str.charAt(index - 1) == str.charAt(cc))
                next[index++] = ++cc;
            else if (next[cc] >= 0)
                cc = next[cc];
            else
                index++;
        }

        return next[N];
    }

}
