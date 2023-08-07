package com.zzzj.contest.week346;

/**
 * @author zzzj
 * @create 2023-08-01 12:01
 */
public class Leet2696 {

    public static void main(String[] args) {

        System.out.println(minLength("ABFCACDB"));

        System.out.println(minLength("ACBBD"));

    }

    public static int minLength(String s) {

        int index;

        while ((index = s.indexOf("AB")) >= 0 || (index = s.indexOf("CD")) >= 0) {
            s = s.substring(0, index) + s.substring(index + 2);
        }

        return s.length();
    }

}
