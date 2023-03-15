package com.zzzj.greedy;

public class Leet2486 {

    public static int appendCharacters(String s, String t) {

        int N = s.length();

        int M = t.length();

        int x = 0;

        int y = 0;

        while (x < N && y < M) {
            char c1 = s.charAt(x);

            char c2 = t.charAt(y);

            if (c1 == c2) {
                x++;
                y++;
            } else {
                x++;
            }
        }

        if (y == M) {
            return 0;
        }

        return M - y;
    }

}
