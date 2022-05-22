package com.zzzj.leet;

import com.zzzj.uf.IUf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-05-22 10:57
 */
public class Leet438 {

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
    }


    public static List<Integer> findAnagrams(String s, String p) {
        if (p.length() > s.length()) {
            return Collections.emptyList();
        }

        int[] table = new int[26];

        for (int i = 0; i < p.length(); i++) {
            table[p.charAt(i) - 'a']++;
        }

        int all = p.length();

        List<Integer> ans = new ArrayList<>();


        int l = 0;
        int r = 0;

        while (r < s.length()) {
            char c = s.charAt(r);

            if (table[c - 'a']-- > 0) {
                all--;
            }

            if (all == 0) {
                ans.add(l);
            }

            r++;

            if (r - l == p.length()) {
                char c2 = s.charAt(l);
                if (++table[c2 - 'a'] > 0) {
                    all++;
                }
                l++;
            }

        }

        return ans;
    }

}
