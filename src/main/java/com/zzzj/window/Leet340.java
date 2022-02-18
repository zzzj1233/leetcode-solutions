package com.zzzj.window;

import com.zzzj.leet.LeetUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-02-18 11:20
 */
public class Leet340 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String str = LeetUtils.newString(18);
            int k = LeetUtils.random.nextInt(str.length());
            if (lengthOfLongestSubstringKDistinct(str, k) != right(str, k)) {
                System.out.println(str);
                System.out.println(k);
                System.out.println("Error");
                return;
            }
        }
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s.length() <= k) {
            return s.length();
        }

        if (k == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>(s.length());

        int l = 0;
        int r = 0;
        int ans = 0;

        while (r < s.length()) {
            char c = s.charAt(r);

            Integer count = map.get(c);
            if (count != null) {
                map.put(c, count + 1);
            } else {
                if (map.size() < k) {
                    map.put(c, 1);
                } else {
                    // 缩小窗口
                    map.put(c, 1);

                    while (map.size() > k) {
                        char c1 = s.charAt(l++);
                        Integer count2 = map.get(c1);
                        if (count2 == 1) {
                            map.remove(c1);
                        } else {
                            map.put(c1, count2 - 1);
                        }
                    }

                }
            }

            ans = Math.max(ans, r - l + 1);
            r++;
        }

        return ans;
    }

    public static int right(String s, int k) {
        int n = s.length();
        if (n * k == 0) return 0;

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

        int max_len = 1;

        while (right < n) {
            // add new character and move right pointer
            hashmap.put(s.charAt(right), right++);

            // slidewindow contains 3 characters
            if (hashmap.size() == k + 1) {
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

}
