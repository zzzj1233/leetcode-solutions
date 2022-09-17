package com.zzzj.greedy;

import java.util.Arrays;

public class Leet1433 {

    public static boolean checkIfCanBreak(String s1, String s2) {

        int N = s1.length();

        Character[] chars1 = new Character[N];
        Character[] chars2 = new Character[N];

        for (int i = 0; i < N; i++) {
            chars1[i] = s1.charAt(i);
            chars2[i] = s2.charAt(i);
        }

        Arrays.sort(chars1);
        Arrays.sort(chars2);

        int i = 1;

        for (; i < N; i++) {
            if (chars1[i] < chars2[i]) {
                break;
            }
        }

        if (i == N) {
            return true;
        }

        for (; i < N; i++) {
            if (chars2[i] < chars1[i]) {
                break;
            }
        }

        return i == N;
    }


}
