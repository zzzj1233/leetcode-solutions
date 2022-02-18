package com.zzzj.window;

import com.zzzj.leet.LeetUtils;

import java.util.Collections;
import java.util.HashMap;

/**
 * @author zzzj
 * @create 2022-02-18 10:57
 */
public class Leet159 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String str = LeetUtils.newString(100);
            if (right(str) != lengthOfLongestSubstringTwoDistinct(str)) {
                System.out.println("error");
                return;
            }
        }
    }

    public static int right(String s) {
        int n = s.length();
        if (n < 3) return n;

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

        int max_len = 2;

        while (right < n) {
            // slidewindow contains less than 3 characters
            if (hashmap.size() < 3)
                hashmap.put(s.charAt(right), right++);

            // slidewindow contains 3 characters
            if (hashmap.size() == 3) {
                // delete the leftmost character
                int del_idx = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(del_idx));
                // move left pointer of the slidewindow
                left = del_idx + 1;
            }

            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }


    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        // 最多包含两个不同字符

        // 最长子串

        if (s.length() <= 2) {
            return s.length();
        }

        char c1 = s.charAt(0);
        char c2 = s.charAt(1);

        int start = 2;

        while (c2 == c1) {
            if (start >= s.length()) {
                return s.length();
            }
            c2 = s.charAt(start);
            start++;
        }

        int ans = start;

        int j = 0;

        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != c1 && c != c2) {
                // 回退到前面只有一个字母
                char previous = s.charAt(i - 1);
                j = i - 2;
                while (s.charAt(j) == previous) {
                    j--;
                }
                j++;
                if (previous == c1) {
                    c2 = c;
                } else {
                    c1 = c;
                }
            }
            ans = Math.max(ans, i - j + 1);
        }

        return ans;
    }

}
