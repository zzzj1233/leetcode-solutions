package com.zzzj.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-01-23 21:38
 */
public class Leet1722 {

    private static class UF {

        private int[] parent;

        // 基于rank,rank比size更适合用于判读树的深度
        private int[] rank;

        private int size;

        public UF(int size) {
            this.parent = new int[size];
            this.rank = new int[size];
            this.size = size;

            // 一开始所有元素都不互通
            for (int i = 0; i < size; i++) {
                this.parent[i] = i;

                // 一开始所有元素的rank都是1
                this.rank[i] = 1;
            }
        }

        public int getSize() {
            return size;
        }

        public void union(int i, int j) {
            int iParent = findParent(i);
            int jParent = findParent(j);

            if (iParent == jParent) {
                return;
            }

            if (rank[iParent] < rank[jParent]) {
                parent[iParent] = jParent;
                // rank不变
            } else if (rank[iParent] > rank[jParent]) {
                parent[jParent] = iParent;
            } else {
                parent[jParent] = iParent;
                rank[iParent] += 1;
                // rank + 1 , 因为是挂在第一个元素下面
            }
        }

        private int findParent(int x) {
            while (x != parent[x]) {
                // old
                // x = parent[x];
                // 压缩路径
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
    }

    public static int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int N = source.length;

        UF uf = new UF(N);

        for (int[] allowedSwap : allowedSwaps) {
            uf.union(allowedSwap[0], allowedSwap[1]);
        }

        Map<Integer, Map<Integer, Integer>> parentMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int parent = uf.findParent(i);

            Map<Integer, Integer> map = parentMap.computeIfAbsent(parent, integer -> new HashMap<>());

            map.put(source[i], map.getOrDefault(source[i], 0) + 1);
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int parent = uf.findParent(i);

            Map<Integer, Integer> map = parentMap.get(parent);

            Integer c = map.get(target[i]);

            if (c != null && c > 0) {
                map.put(target[i], c - 1);
            } else {
                ans++;
            }
        }

        return ans;
    }

}
