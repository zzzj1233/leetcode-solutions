package com.zzzj.window;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zzzj
 * @create 2021-12-19 14:19
 */
public class Leet3 {

    public static void main(String[] args) {
        String str = LeetUtils.randomString(20);
        System.out.println(str);
        System.out.println(lengthOfLongestSubstring(str));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> recorder = new HashMap<>(26);

        int ans = 0;

        int l = 0;
        int r = 0;

        for (; r < s.length(); r++) {
            char c = s.charAt(r);
            Integer index = recorder.get(c);
            if (index != null) {
                for (; l <= index; l++) {
                    recorder.remove(s.charAt(l));
                }
            }
            recorder.put(c, r);
            ans = Math.max(ans, r - l + 1);
        }

        return ans;
    }

}
