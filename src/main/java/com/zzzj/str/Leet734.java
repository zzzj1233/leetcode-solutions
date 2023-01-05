package com.zzzj.str;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-01-04 11:47
 */
public class Leet734 {

    public static boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {

        if (sentence1.length != sentence2.length) {
            return false;
        }

        Map<String, Set<String>> similar = new HashMap<>();

        for (List<String> similarPair : similarPairs) {
            String w1 = similarPair.get(0);
            String w2 = similarPair.get(1);

            similar.computeIfAbsent(w1, s -> new HashSet<>())
                    .add(w2);

            similar.computeIfAbsent(w2, s -> new HashSet<>())
                    .add(w1);
        }

        for (int i = 0; i < sentence1.length; i++) {
            String w1 = sentence1[i];
            String w2 = sentence2[i];

            if (w1.equals(w2)) {
                continue;
            }

            Set<String> set = similar.get(w1);

            if (set == null || !set.contains(w2)) {
                return false;
            }
        }

        return true;
    }

}
