package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-05-09 10:38
 */
public class Leet266 {

    public static void main(String[] args) {
        System.out.println(0 % 2);
    }

    public static boolean canPermutePalindrome(String s) {

        int N = s.length();

        int[] table = new int[256];

        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i)]++;
        }

        boolean allow = N % 2 != 0;

        for (int i = 0; i < table.length; i++) {
            if (table[i] % 2 != 0) {
                if (!allow) {
                    return false;
                }
                allow = false;
            }
        }

        return true;
    }

}
