package com.zzzj.contest.week364;

public class Q1 {

    public static void main(String[] args) {

        System.out.println(maximumOddBinaryNumber("010"));

        System.out.println(maximumOddBinaryNumber("0101"));

    }

    public static String maximumOddBinaryNumber(String s) {
        int N = s.length();

        int cnt = 0;

        for (int i = 0; i < N; i++)
            if (s.charAt(i) == '1')
                cnt++;

        StringBuilder builder = new StringBuilder(N);

        for (int i = 0; i < cnt - 1; i++) {
            builder.append('1');
        }

        for (int i = cnt - 1; i < N - 1; i++) {
            builder.append('0');
        }

        builder.append('1');

        return builder.toString();
    }

}
