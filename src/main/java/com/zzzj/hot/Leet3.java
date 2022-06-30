package com.zzzj.hot;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zzzj
 * @create 2022-01-15 21:35
 */
public class Leet3 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring(""));
    }

    public static int lengthOfLongestSubstring(String s) {
        int[] table = new int[256];

        int l = 0;
        int r = 0;

        int ans = 0;

        while (r < s.length()) {
            char c = s.charAt(r);

            if (table[c] != 0) {

                int end = table[c] + 1;

                while (l < end) {
                    table[s.charAt(l)] = 0;
                    l++;
                }

            }

            table[c] = r;

            ans = Math.max(ans, r - l + 1);

            r++;
        }

        return ans;
    }

}
