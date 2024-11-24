package com.zzzj.contest.week425;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2024-11-24 10:35
 */
public class Q2 {

    public static void main(String[] args) {

        System.out.println(isPossibleToRearrange("abcd", "cdab", 2));

        System.out.println(isPossibleToRearrange("aabbcc", "bbaacc", 3));

        System.out.println(isPossibleToRearrange("aabbcc", "bbaacc", 2));

    }

    public static boolean isPossibleToRearrange(String s, String t, int k) {

        int N = s.length();

        Map<String, Integer> words = new HashMap<>();

        Map<String, Integer> words2 = new HashMap<>();

        int cnt = N / k;

        for (int i = 0; i < k; i++) {
            String word = s.substring(i * cnt, (i + 1) * cnt);

            words.put(word, words.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < k; i++) {
            String word = t.substring(i * cnt, (i + 1) * cnt);

            words2.put(word, words2.getOrDefault(word, 0) + 1);
        }

        return words.equals(words2);
    }

}
