package com.zzzj.greedy;

public class Leet1946 {

    public static void main(String[] args) {
        System.out.println(maximumNumber("123", new int[]{9, 8, 5, 0, 3, 6, 4, 2, 6, 8}));
    }

    public static String maximumNumber(String num, int[] change) {
        StringBuilder builder = new StringBuilder(num);

        int N = num.length();

        int i = 0;
        for (; i < N; i++) {
            int c = Character.digit(builder.charAt(i), 10);
            if (change[c] > c) {
                builder.setCharAt(i, Character.forDigit(change[c], 10));
                i++;
                break;
            }
        }

        while (i < N) {
            int c = Character.digit(builder.charAt(i), 10);
            if (change[c] < c) {
                break;
            }
            builder.setCharAt(i, Character.forDigit(change[c], 10));
            i++;
        }

        return builder.toString();
    }

}
