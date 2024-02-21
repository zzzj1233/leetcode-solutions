package com.zzzj.str;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-02-01 17:01
 */
public class Leet2301 {

    public static void main(String[] args) {

        System.out.println(matchReplacement("fool3e7bar", "leet", LeetUtils.convertChars("[[\"e\",\"3\"],[\"t\",\"7\"],[\"t\",\"8\"]]")));

    }

    public static boolean matchReplacement(String s, String sub, char[][] mappings) {

        int N = s.length();

        int M = sub.length();

        Map<Character, Set<Character>> map = new HashMap<>();

        for (char[] mapping : mappings)
            map.computeIfAbsent(mapping[0], character -> new HashSet<>()).add(mapping[1]);

        for (int i = 0; i + M <= N; i++)
            if (check(s.substring(i, i + M), sub, map))
                return true;

        return false;
    }

    public static boolean check(String s1, String sub, Map<Character, Set<Character>> map) {

        int N = sub.length();

        for (int i = 0; i < N; i++) {
            if (s1.charAt(i) == sub.charAt(i))
                continue;

            if (map.getOrDefault(sub.charAt(i), Collections.emptySet()).contains(s1.charAt(i)))
                continue;

            return false;
        }

        return true;
    }

}
