package com.zzzj.window;

/**
 * @author Zzzj
 * @create 2021-12-19 20:08
 */
public class Leet28 {

    public static void main(String[] args) {
        System.out.println(strStr("baab", "ab"));
    }

    public static int strStr(String haystack, String needle) {

        if (haystack.isEmpty() && needle.isEmpty()) {
            return 0;
        }

        int n = 0;

        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {

            for (int j = i; j < haystack.length() && n < needle.length() && haystack.charAt(j) == needle.charAt(n); j++, n++) {
                if (n == needle.length() - 1) {
                    return i;
                }
            }
            n = 0;
        }

        return -1;
    }

}
