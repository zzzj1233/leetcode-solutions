package com.zzzj.contest.dweek111;

public class Leet8014 {

    public static void main(String[] args) {

        System.out.println(canMakeSubsequence("abc", "ad"));

        System.out.println(canMakeSubsequence("zc", "ad"));

        System.out.println(canMakeSubsequence("ab", "d"));

        System.out.println(canMakeSubsequence("c", "b"));

    }

    public static boolean canMakeSubsequence(String str1, String str2) {

        int N = str1.length();

        int M = str2.length();

        int j = 0;

        for (int i = 0; i < N && j < M; i++) {

            char c1 = str1.charAt(i);
            char c2 = str2.charAt(j);

            if (c1 == c2)
                j++;
            else if (c1 + 1 == c2)
                j++;
            else if (c2 == 'a' && c1 == 'z')
                j++;
        }

        return j == M;
    }

}
