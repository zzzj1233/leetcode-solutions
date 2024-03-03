package com.zzzj.str;

public class KMP {

    public static int search(String str, String search) {

        int N = str.length();

        int M = search.length();

        int[] next = next(search);

        int x = 0;

        int y = 0;

        while (x < N && y < M) {
            if (str.charAt(x) == search.charAt(y)) {
                x++;
                y++;
            } else if (next[y] >= 0) {
                y = next[y];
            } else {
                x++;
            }
        }

        return y == M ? x - M : -1;
    }

    public static int[] next(String str) {

        int N = str.length();

        int[] next = new int[N + 1];

        next[0] = -1;

        int index = 2;

        int cc = 0;

        while (index <= N) {
            if (str.charAt(index - 1) == str.charAt(cc)) {
                next[index++] = ++cc;
            } else if (next[cc] >= 0) {
                cc = next[cc];
            } else {
                index++;
            }
        }

        return next;
    }

}
