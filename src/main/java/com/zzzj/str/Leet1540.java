package com.zzzj.str;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-04-28 18:06
 */
public class Leet1540 {

    public static void main(String[] args) {
        System.out.println((int) 'z');
        System.out.println((int) 'a');
    }

    public static boolean canConvertString(String s, String t, int k) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] bucket = new int[26];

        Arrays.fill(bucket, 1);

        int N = s.length();

        for (int i = 0; i < N; i++) {

        }

        return false;
    }

    public static int diff(char c1, char c2) {
        
        return -1;
    }

}
