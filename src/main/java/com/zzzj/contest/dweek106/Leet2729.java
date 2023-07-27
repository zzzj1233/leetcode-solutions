package com.zzzj.contest.dweek106;

/**
 * @author zzzj
 * @create 2023-07-27 11:03
 */
public class Leet2729 {


    public static void main(String[] args) {

        System.out.println(isFascinating(192));

        System.out.println(isFascinating(100));

        System.out.println(isFascinating(267));

    }

    public static boolean isFascinating(int n) {

        boolean[] tab = new boolean[11];

        String str = String.valueOf(n).concat(String.valueOf(n << 1)).concat(String.valueOf(n * 3));

        for (int i = 0; i < str.length(); i++) {
            int digit = Character.digit(str.charAt(i), 10);
            if (tab[digit]) return false;
            tab[digit] = true;
        }

        return !tab[0];
    }

}
