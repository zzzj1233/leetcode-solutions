package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2023-01-21 18:08
 */
public class Leet1733 {

    public static void main(String[] args) {
        System.out.println(minimumTeachings(
                3,
                LeetUtils.convertInts("[[1],[2],[3],[2]]"),
                LeetUtils.convertInts("[[3,4]]")
        ));
    }

    public static int minimumTeachings(int n, int[][] languages, int[][] friendships) {

        int N = languages.length;

        Set<Integer>[] langSet = new Set[N];

        for (int i = 0; i < N; i++) {
            langSet[i] = new HashSet(languages[i].length);

            for (int j = 0; j < languages[i].length; j++) {
                langSet[i].add(languages[i][j]);
            }

        }

        Set<Integer> unCommunicate = new HashSet<>();

        // 无法沟通的人
        LOOP1:
        for (int i = 0; i < friendships.length; i++) {
            int x = friendships[i][0] - 1;
            int y = friendships[i][1] - 1;
            // x和y是朋友
            Set<Integer> langX = langSet[x];
            Set<Integer> langY = langSet[y];
            for (Integer it : langX) {
                if (langY.contains(it)) {
                    continue LOOP1;
                }
            }

            // 这两个人无法沟通
            unCommunicate.add(x);
            unCommunicate.add(y);
        }

        // 所有的无法沟通的人会的最多的一门语言
        Map<Integer, Integer> rank = new HashMap<>();

        int maxKey = -1;

        rank.put(maxKey, 0);

        for (Integer id : unCommunicate) {

            for (Integer lang : langSet[id]) {
                Integer used = rank.getOrDefault(lang, 0);
                if (used + 1 > rank.get(maxKey)) {
                    maxKey = lang;
                }
                rank.put(lang, used + 1);
            }

        }

        Integer max = rank.get(maxKey);

        return unCommunicate.size() - max;
    }

}
