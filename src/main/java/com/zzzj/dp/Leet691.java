package com.zzzj.dp;

import java.util.*;

/**
 * @author Zzzj
 * @create 2022-06-18 13:42
 */
public class Leet691 {

    public static void main(String[] args) {
//        System.out.println(minStickers(new String[]{"with", "example", "science"}, "thehat"));
//        System.out.println(minStickers(new String[]{"ba", "c", "abcd"}, "babac"));
        System.out.println(minStickers(new String[]{"notice", "possible"}, "basicbasic"));
    }


    public static int minStickers(String[] stickers, String target) {
        int N = stickers.length;

        int[][] counts = new int[N][26];

        for (int i = 0; i < N; i++) {
            //str 贴纸数组
            String sticker = stickers[i];

            for (int j = 0; j < sticker.length(); j++) {
                counts[i][sticker.charAt(j) - 'a']++;
            }

        }

        return Math.max(-1, dfs(counts, target, new HashMap<>()));
    }

    public static int dfs(int[][] counts, String target, Map<String, Integer> memo) {
        Integer cache = memo.get(target);

        if (cache != null) {
            return cache;
        }

        int ans = Integer.MAX_VALUE;

        int[] tar = new int[26];

        char[] chars = target.toCharArray();

        for (char c : chars) {
            tar[c - 'a']++;
        }

        for (int[] count : counts) {
            StringBuilder builder = new StringBuilder(target.length());

            boolean anyMatch = false;

            for (int i = 0; i < tar.length; i++) {
                if (tar[i] > 0) {

                    int max = Math.max(0, tar[i] - count[i]);

                    for (int j = 0; j < max; j++) {
                        builder.append(((char) (i + 'a')));
                    }

                }
            }

            if (builder.length() == target.length()) {
                continue;
            }

            ans = Math.min(ans, 1 + (builder.length() == 0 ? 0 : dfs(counts, builder.toString(), memo)));
        }

        memo.put(target, ans);

        return ans;
    }

}
