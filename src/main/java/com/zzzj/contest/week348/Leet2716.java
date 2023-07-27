package com.zzzj.contest.week348;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2023-07-27 17:29
 */
public class Leet2716 {

    public static void main(String[] args) {

        System.out.println(minimizedStringLength("aaabc"));

        System.out.println(minimizedStringLength("cbbd"));

        System.out.println(minimizedStringLength("dddaaa"));

    }

    public static int minimizedStringLength(String s) {

        Map<Character, TreeSet<Integer>> indexes = new HashMap<>();

        int N = s.length();

        for (int i = 0; i < N; i++) {
            indexes.computeIfAbsent(s.charAt(i), character -> new TreeSet<>())
                    .add(i);
        }

        int ans = N;

        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);

            if (!indexes.get(c).contains(i)) continue;

            Integer floor = indexes.get(c).floor(i - 1);

            if (floor != null) {
                ans--;
                indexes.get(c).remove(floor);
            }

            Integer ceil = indexes.get(c).ceiling(i + 1);

            if (ceil != null) {
                ans--;
                indexes.get(c).remove(ceil);
            }
        }

        return ans;
    }

}
