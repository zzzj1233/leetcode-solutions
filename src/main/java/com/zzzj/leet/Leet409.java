package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-07-01 14:42
 */
public class Leet409 {

    public static int longestPalindrome(String s) {

        int[] table = new int[128];

        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i)]++;
        }

        int ans = 0;

        for (int i = 0; i < table.length; i++) {
            ans += table[i] / 2;
        }

        return ans == s.length() ? ans : ans + 1;
    }

}
