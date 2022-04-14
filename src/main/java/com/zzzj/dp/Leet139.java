package com.zzzj.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-04-12 16:30
 */
public class Leet139 {

    static HashSet<char[]>[] table;

    public static boolean contains(char[] chars, int i, int j) {
        HashSet<char[]> set = table[j - i + 1];
        if (set == null) {
            return false;
        }
        return false;
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        table = new HashSet[26];

        for (String word : wordDict) {
            Set<char[]> set = null;
            if ((set = table[word.length()]) == null) {
                table[word.length()] = new HashSet<>();
                set = table[word.length()];
            }
            set.add(word.toCharArray());
        }

        return false;
    }

}
