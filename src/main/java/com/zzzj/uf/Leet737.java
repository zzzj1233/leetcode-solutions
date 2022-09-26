package com.zzzj.uf;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-09-19 14:52
 */
public class Leet737 {

    public static class UF implements IUf {

        private final int n;
        int[] parents;
        int[] rank;

        public UF(int N) {
            parents = new int[N];
            rank = new int[N];
            n = N;
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }
        }

        @Override
        public int getSize() {
            return n;
        }

        public void union(int x, int y) {
            int parentX = getParent(x);
            int parentY = getParent(y);

            if (parentX == parentY) {
                return;
            }

            if (rank[parentX] > rank[parentY]) {
                parents[parentY] = parentX;
            } else if (rank[parentX] < rank[parentY]) {
                parents[parentX] = parentY;
            } else {  // 层级一样,挂载x上
                parents[parentY] = parentX;
                rank[parentX]++;
            }
        }

        public boolean isConnected(int x, int y) {
            return getParent(x) == getParent(y);
        }

        private int getParent(int index) {
            while (parents[index] != index) {
                // 路径压缩
                parents[index] = parents[parents[index]];
                index = parents[index];
            }
            return index;
        }

    }

    public static boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }

        Map<String, Integer> wordIndex = new HashMap<>();

        UF uf = new UF(similarPairs.size() << 1);

        int count = 0;

        for (List<String> pair : similarPairs) {
            String str1 = pair.get(0);
            String str2 = pair.get(1);
            if (!wordIndex.containsKey(str1)) {
                wordIndex.put(str1, count++);
            }
            if (!wordIndex.containsKey(str2)) {
                wordIndex.put(str2, count++);
            }
            uf.union(wordIndex.get(str1), wordIndex.get(str2));
        }

        int N = sentence1.length;

        for (int i = 0; i < N; i++) {
            String word1 = sentence1[i];
            String word2 = sentence2[i];

            if (word1.equals(word2)) {
                continue;
            }

            if (!wordIndex.containsKey(word1) || !wordIndex.containsKey(word2)) {
                return false;
            }

            if (!uf.isConnected(wordIndex.get(word1), wordIndex.get(word2))) {
                return false;
            }

        }

        return true;
    }

}
