package com.zzzj.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-02-15 16:44
 */
public class Leet291 {

    public static void main(String[] args) {
        // System.out.println(wordPatternMatch("abab", "redblueredblue"));
        // System.out.println(wordPatternMatch("aaaa", "asdasdasdasd"));
        System.out.println(wordPatternMatch("aba", "aaaa"));
        System.out.println(wordPatternMatch("ab", "aa"));
    }

    public static boolean ans;

    public static boolean wordPatternMatch(String pattern, String s) {
        ans = false;

        process(pattern.toCharArray(), s.toCharArray(), new String[26], 0, 0, new HashSet<>());

        return ans;
    }

    public static void process(char[] pattern, char[] s, String[] table, int patternIndex, int sIndex, Set<String> used) {
        if (ans) {
            return;
        }

        if (sIndex >= s.length) {
            if (patternIndex >= pattern.length) {
                ans = true;
            }
            return;
        }

        if (patternIndex >= pattern.length) {
            if (sIndex >= s.length) {
                ans = true;
            }
            return;
        }

        char c = pattern[patternIndex];

        // initTable
        if (table[c - 'a'] == null) {
            // 尝试1个
            StringBuilder builder = new StringBuilder(s.length - sIndex);
            for (int i = sIndex; i < s.length && !ans; i++) {
                builder.append(s[i]);
                table[c - 'a'] = builder.toString();
                if (!used.add(table[c - 'a'])) {
                    continue;
                }
                process(pattern, s, table, patternIndex + 1, i + 1, used);
                used.remove(table[c - 'a']);
            }
            table[c - 'a'] = null;
        } else {
            String str = table[c - 'a'];

            if (s.length - sIndex < str.length()) {
                return;
            }

            for (int i = 0; i < str.length(); i++) {
                if (s[i + sIndex] != str.charAt(i)) {
                    return;
                }
            }

            process(pattern, s, table, patternIndex + 1, sIndex + str.length(), used);
        }
    }

}
