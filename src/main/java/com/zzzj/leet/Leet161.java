package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-07-02 17:54
 */
public class Leet161 {

    public static void main(String[] args) {
        System.out.println(isOneEditDistance("abcd", "abc"));
        System.out.println(isOneEditDistance("cab", "ad"));
    }

    public static boolean isOneEditDistance(String s, String t) {
        String longer = s.length() >= t.length() ? s : t;

        String shorter = longer == s ? t : s;

        boolean allow = true;

        if (longer.length() - shorter.length() > 1) {
            return false;
        }


        int i = 0;
        int j = 0;

        while (i < longer.length() && j < shorter.length()) {
            char c = longer.charAt(i);
            char c2 = shorter.charAt(j);

            if (c != c2) {
                if (!allow) {
                    return false;
                }
                allow = false;
                if (longer.length() != shorter.length()) {
                    i++;
                    continue;
                }
            }

            i++;
            j++;
        }

        if (i == longer.length() && j == shorter.length()) {
            return !allow;
        }

        return allow;
    }

}
