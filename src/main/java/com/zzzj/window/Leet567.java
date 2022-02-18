package com.zzzj.window;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-01-11 17:21
 */
public class Leet567 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String s1 = LeetUtils.newString(3);
            String s2 = LeetUtils.newString(30);
            boolean me = checkInclusion(s1, s2);
            boolean ans = violent(s1, s2);
            if (me != ans) {
                System.out.println("\"" + s1 + "\"");
                System.out.println("\"" + s2 + "\"");
                System.out.println("me = " + me + " , ans = " + ans);
            }
        }
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] table = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            table[c - 'a']++;
        }

        int[] tb2 = new int[26];
        int l = 0;
        int r = 0;

        while (r < s2.length()) {
            char c = s2.charAt(r);
            int index = c - 'a';

            tb2[index]++;

            if (table[index] == 0) {
                while (l <= r) {
                    tb2[s2.charAt(l) - 'a']--;
                    l++;
                }
            } else if (tb2[index] > table[index]) {
                int idx;
                while ((idx = s2.charAt(l) - 'a') != index) {
                    tb2[idx]--;
                    l++;
                }
                tb2[idx]--;
                l++;
            }
            if (r - l + 1 == s1.length()) {
                return true;
            }
            r++;
        }

        return false;
    }

    public static boolean violent(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }


}
