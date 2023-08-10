package com.zzzj.contest.week355;

import java.util.*;

public class Leet6942 {

    public static void main(String[] args) {

        System.out.println(countPalindromePaths(Arrays.asList(-1, 0, 0, 1, 1, 2), "tiaiai"));
//
        System.out.println(countPalindromePaths(Arrays.asList(-1, 0, 0, 0, 0), "tiaia"));

        System.out.println(countPalindromePaths(Arrays.asList(-1, 6, 8, 5, 0, 4, 2, 0, 4), "tiaiaivea"));

    }

    // N个偶数字母 + 1个奇数字母
    // N个偶数字母 + 0个奇数字母
    // x == 0 || Integer.bitCount(x) == 1
    public static long countPalindromePaths(List<Integer> parent, String s) {

        // X -> Y = ROOT -> X ^ ROOT -> Y = ROOT ^ X ^ ROOT ^ Y = X ^ Y

        Map<Integer, Set<Integer>> parentMap = new HashMap<>();

        for (int i = 1; i < parent.size(); i++) {
            Integer p = parent.get(i);
            parentMap.computeIfAbsent(p, integer -> new HashSet<>())
                    .add(i);
        }

        ans = 0;

        HashMap<Integer, Long> map = new HashMap<>();

        map.put(0, 1L);

        for (Integer it : parentMap.get(0)) {
            dfs(parentMap, s, it, 0, map);
        }

        return ans;
    }

    static long ans;

    public static void dfs(Map<Integer, Set<Integer>> parentMap,
                           String str,
                           int index,
                           int path,
                           Map<Integer, Long> rec
    ) {

        if (index >= str.length())
            return;

        int strIndex = str.charAt(index) - 'a';
        int newPath = path ^ (1 << strIndex);

        // ac -> ca = acca
        Long old = rec.getOrDefault(newPath, 0L);

        ans += old;

        rec.put(newPath, old + 1);

        // ac -> a  = aca
        // ac -> c  = cac

        for (int i = 0; i < 32; i++) {
            ans += rec.getOrDefault(newPath ^ (1 << i), 0L);
        }

        Set<Integer> children = parentMap.getOrDefault(index, Collections.emptySet());

        for (Integer child : children) {
            dfs(parentMap, str, child, newPath, rec);
        }

    }

}
