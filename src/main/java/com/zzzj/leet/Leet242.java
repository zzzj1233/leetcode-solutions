package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-09-23 10:02
 */
public class Leet242 {

    public static void main(String[] args) {
        System.out.println(isAnagram("aacc", "ccac"));
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] table = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            table[c]++;
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            table[c]--;
        }

        for (int i = 0; i < table.length; i++) {
            if (table[i] != 0){
                return false;
            }
        }

        return true;
    }

}
