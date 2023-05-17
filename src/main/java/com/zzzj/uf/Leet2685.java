package com.zzzj.uf;

import com.zzzj.leet.LeetUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2023-05-17 12:17
 */
public class Leet2685 {

    public static void main(String[] args) {
        System.out.println(countCompleteComponents(
                6,
                LeetUtils.convertInts("[[0,1],[0,2],[1,2],[3,4]]")
        ));

        System.out.println(countCompleteComponents(
                6,
                LeetUtils.convertInts("[[0,1],[0,2],[1,2],[3,4],[3,5]]")
        ));
    }

    public static int countCompleteComponents(int n, int[][] edges) {

        Map<Integer, Set<Integer>> graph = new HashMap<>(n);

        UF uf = new UF(n);

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], integer -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], integer -> new HashSet<>()).add(edge[0]);
            uf.union(edge[0], edge[1]);
        }

        int ans = 0;

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            Set<Integer> adj = graph.get(i);

            if (adj == null) {
                continue;
            }

            if (visited[uf.getParent(i)]) {
                continue;
            }

            for (int j = 0; j < n; j++) {

                if (j == i) {
                    continue;
                }

                if (uf.isConnected(i, j) && !adj.contains(j)) {
                    visited[uf.getParent(i)] = true;
                }

            }

        }

        for (int p : uf.parents) {
            if (!visited[p]) {
                visited[p] = true;
                ans++;
            }
        }

        return ans;
    }

    private static class UF {

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

}
