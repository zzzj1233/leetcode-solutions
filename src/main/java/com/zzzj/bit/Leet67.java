package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2022-08-01 17:52
 */
public class Leet67 {

    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
        System.out.println(addBinary("1010", "1011"));
    }

    public static String addBinary(String a, String b) {

        int N = a.length();
        int M = b.length();

        StringBuilder builder = new StringBuilder(Math.max(N, M) + 1);

        int i = N - 1;
        int j = M - 1;

        boolean extra = false;

        while (i >= 0 || j >= 0) {
            if (i < 0) {
                char c = b.charAt(j);
                extra = append(builder, '0', c, extra);
                j--;
            } else if (j < 0) {
                char c = a.charAt(i);
                extra = append(builder, '0', c, extra);
                i--;
            } else {
                extra = append(builder, a.charAt(i), b.charAt(j), extra);
                i--;
                j--;
            }
        }

        if (extra) {
            builder.append('1');
        }

        return builder.reverse().toString();
    }

    public static boolean append(StringBuilder builder, char char1, char char2, boolean extra) {
        if (char1 == char2) {
            builder.append(extra ? '1' : '0');
            return char1 == '1';
        } else {
            builder.append(extra ? '0' : '1');
            return extra;
        }
    }

}
