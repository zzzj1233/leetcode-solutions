package com.zzzj.arr;

/**
 * @author Zzzj
 * @create 2021-12-20 21:11
 */
public class Leet344 {

    public static void reverseString(char[] s) {

        for (int i = 0, j = s.length - 1; i < s.length / 2; i++, j--) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }

    }

}
