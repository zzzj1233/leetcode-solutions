package com.zzzj.window;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2021-12-22 14:29
 */
public class Leet395 {

    public static void main(String[] args) {

        int ans = 0;

        String s = LeetUtils.newString(10);

        int k = LeetUtils.random.nextInt(1) + 2;

        System.out.println(longestSubstring("tmqqbnxpne", 2));
    }

    /**
     *
     */
    public static int longestSubstring(String s, int k) {

        int ans = 0;

        for (int len = 1; len <= 26; len++) {
            int[] table = new int[26];

            int l = 0;
            int r = 0;

            int cur = 0;
            int matchCount = 0;

            while (r < s.length()) {
                char c = s.charAt(r);
                if (table[c - 97] == 0) {
                    cur++;
                }
                table[c - 97]++;
                if (cur > len) {
                    // 移动Left,直到cur = len
                    while (cur != len) {
                        int leftIdx = (int) s.charAt(l) - 97;
                        if (table[leftIdx] == k) {
                            matchCount--;
                        }
                        table[leftIdx]--;
                        if (table[leftIdx] == 0) {
                            cur--;
                        }
                        l++;
                    }
                } else {
                    if (table[c - 97] == k) {
                        matchCount++;
                    }
                }

                if (matchCount == cur) {
                    ans = Math.max(ans, r - l + 1);
                }

                r++;
            }

        }

        return ans;
    }


}
