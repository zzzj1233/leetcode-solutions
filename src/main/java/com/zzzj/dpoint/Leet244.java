package com.zzzj.dpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-04-21 18:49
 */
public class Leet244 {

    static class WordDistance {


        private final HashMap<String, List<Integer>> map;

        public WordDistance(String[] wordsDict) {

            this.map = new HashMap<String, List<Integer>>(wordsDict.length);

            for (int i = 0; i < wordsDict.length; i++) {
                String word = wordsDict[i];
                map.computeIfAbsent(word, k -> new ArrayList<>()).add(i);
            }

        }

        public int shortest(String word1, String word2) {
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);

            int ans = Integer.MAX_VALUE;

            for (Integer i1 : list1) {
                for (Integer i2 : list2) {
                    ans = Math.min(ans, Math.abs(i1 - i2));
                }
            }

            return ans;
        }

    }


}
