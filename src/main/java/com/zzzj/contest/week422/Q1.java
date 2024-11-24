package com.zzzj.contest.week422;

/**
 * @author zzzj
 * @create 2024-11-03 10:33
 */
public class Q1 {

    public static void main(String[] args) {

        System.out.println(isBalanced("1234"));

        System.out.println(isBalanced("24123"));

    }

    public static boolean isBalanced(String num) {

        int v = 0;

        for (int i = 0; i < num.length(); i++) {
            int digit = Character.digit(num.charAt(i), 10);
            if (i % 2 == 0)
                v += digit;
            else
                v -= digit;
        }

        return v == 0;
    }

}
