package com.zzzj.hot;


import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-01-24 11:24
 */
public class Leet14 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String[] arr = {
                    LeetUtils.newString(100, 5),
                    LeetUtils.newString(100, 5),
                    LeetUtils.newString(100, 5)
            };

            String violent = violent(arr);
            String ans = longestCommonPrefix(arr);

            if (!violent.equals(ans)) {
                System.out.println(Arrays.toString(arr));
            }

        }
    }

    public static String violent(String[] arr) {
        if (arr.length == 0) {
            return "";
        }

        if (arr.length == 1) {
            return arr[0];
        }

        int[] hash = new int[26];
        int k = 0;

        String str1 = arr[0];

        StringBuffer result = new StringBuffer(arr.length);

        while (k < str1.length()) {

            char c = ' ';
            for (int i = 0; i < arr.length; i++) {
                if (k >= arr[i].length()) {
                    k = str1.length();
                    break;
                }

                if (i == 0) {
                    c = arr[i].charAt(k);
                    hash[c - 97] = 1;
                } else {
                    char c2 = arr[i].charAt(k);
                    if (c2 != c) {
                        k = str1.length();
                        break;
                    } else {
                        if (i == arr.length - 1) {
                            result.append(c);
                        }
                    }
                }
            }
            k++;
        }


        return result.toString();
    }

    public static String longestCommonPrefix(String[] strs) {
        int r = strs[0].length();

        char[] origin = strs[0].toCharArray();

        for (int i = 1; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            if (r > chars.length) {
                r = chars.length;
            }
            for (int j = r - 1; j >= 0; j--) {
                if (origin[j] != chars[j]) {
                    r = j;
                }
            }
            if (r == 0) {
                return "";
            }
            // abcdefg
            // abc
        }

        return strs[0].substring(0, r);
    }

}
