package com.zzzj.window;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-01-14 23:41
 */
public class Leet438 {

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagrams("abab", "ab"));
    }


    public static boolean isMatch(int[] t1, int[] t2) {
        for (int i = 0; i < t1.length; i++) {
            if (t1[i] != t2[i]) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> findAnagrams(String s, String p) {

        int[] table = new int[26];

        for (int i = 0; i < p.length(); i++) {
            table[p.charAt(i) - 97]++;
        }

        int[] record = new int[26];

        for (int i = 0; i < p.length(); i++) {
            record[s.charAt(i) - 97]++;
        }

        int l = 1;

        int r = p.length();

        List<Integer> ans = new ArrayList<>();

        if (isMatch(record, table)) {
            ans.add(0);
        }

        while (r < s.length()) {
            record[s.charAt(l - 1) - 97]--;
            record[s.charAt(r) - 97]++;
            if (isMatch(record, table)) {
                ans.add(l);
            }
            l++;
            r++;
        }

        return ans;
    }

}
