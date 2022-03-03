package com.zzzj.window;

import com.zzzj.leet.LeetUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zzzj
 * @create 2022-02-19 21:54
 */
public class Leet1100 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String s = LeetUtils.randomString(50, false);
            int k = LeetUtils.random.nextInt(15) + 1;
            if (numKLenSubstrNoRepeats(s, k) != right(s, k)) {
                System.out.println("Error");
                System.out.println(s);
                System.out.println(k);
                System.out.println(numKLenSubstrNoRepeats(s, k));
                return;
            }
        }

    }

    public static int numKLenSubstrNoRepeats(String s, int k) {
        if (s.length() < k) {
            return 0;
        }

        int ans = 0;
        int l = 0;
        int r = 0;

        int[] table = new int[26];

        while (r < s.length()) {

            while (r < s.length() && r - l < k) {
                char c = s.charAt(r);
                int index = c - 'a';
                // l跳过上一个c的位置
                if (table[index] > 0) {
                    while (s.charAt(l) != c) {
                        table[s.charAt(l) - 'a'] = 0;
                        l++;
                    }
                    l++;
                }
                table[index] = 1;
                r++;
            }
            if (r - l == k) {
                ans++;
            }
            if (l < s.length()) {
                table[s.charAt(l) - 'a']--;
            }
            l++;
        }

        return ans;
    }

    public static int right(String S, int K) {
        // 和无重复最长的子串类似，使用滑动窗口
        int length = S.length();

        int res = 0;
        int left = 0;
        Map<Character, Integer> window = new HashMap<>();
        for (int right = 0; right < length; right++) {
            char ch = S.charAt(right);
            window.put(ch, window.getOrDefault(ch, 0) + 1);
            // 添加的无重复，且窗口长度为K, 则累加，并将窗口整体右移，继续判断后面的
            if (window.get(ch) == 1 && (right - left + 1 == K)) {
                res++;
                window.put(S.charAt(left), window.get(S.charAt(left)) - 1);
                left++;
                continue;
            }

            // 有重复则整体右移，直到把重复的那个给排除在外
            while (window.get(ch) > 1) {
                window.put(S.charAt(left), window.get(S.charAt(left)) - 1);
                left++;
            }

        }

        return res;
    }


}
