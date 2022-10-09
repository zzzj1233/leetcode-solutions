package com.zzzj.greedy;

public class Leet1663 {

    public static void main(String[] args) {
//        System.out.println(getSmallestString(6, 30));
        System.out.println(getSmallestString(5, 30));
        System.out.println(getSmallestString(4, 30));
    }

    public static String getSmallestString(int n, int k) {
        StringBuilder builder = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            builder.append('a');
        }

        int num = k;

        int end = n - 1;

        while (num - 25 >= n) {
            num -= 25;
            builder.setCharAt(end, 'z');
            end--;
        }

        int remain = num - n;

        if (end >= 0) {
            builder.setCharAt(end, (char) ('a' + remain));
        }

        return builder.toString();
    }

}
