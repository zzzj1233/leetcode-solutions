package com.zzzj.window;

/**
 * @author zzzj
 * @create 2021-12-16 11:15
 */
public class Leet1736 {

    public static void main(String[] args) {
        System.out.println(longestNiceSubstring("jcl"));
    }

    private static boolean isMatch(int[] table) {
        for (int i = 0; i < 26; i++) {
            if (table[i] > 0 && table[i + 32] == 0) {
                return false;
            }
        }
        for (int i = 32; i < 58; i++) {
            if (table[i] > 0 && table[i - 32] == 0) {
                return false;
            }
        }

        return true;
    }

    public static String longestNiceSubstring(String s) {
        if (s == null || s.length() < 2) {
            return "";
        }

        int l = 0;
        int r = 0;

        // 大写和小写同时出现
        for (int i = 0; i < s.length(); i++) {

            int[] table = new int[58];

            char c = s.charAt(i);

            table[c - 65] = 1;

            for (int j = i + 1; j < s.length(); j++) {
                char c2 = s.charAt(j);
                table[c2 - 65]++;
                if (isMatch(table)) {
                    if (j - i > r - l) {
                        r = j;
                        l = i;
                    }
                }
            }

        }

        return r == l ? "" : s.substring(l, r + 1);
    }

}
