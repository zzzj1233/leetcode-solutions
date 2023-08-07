package com.zzzj.contest.week345;

import com.zzzj.leet.LeetUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2023-08-01 15:25
 */
public class Leet2685 {

    public static void main(String[] args) {

        System.out.println(countCompleteComponents(1, new int[0][0]));

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

    public static int countCompleteComponents(int n, int[][] edges) {

        // 每对节点之间都存在一条边
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        UF uf = new UF(n);

        for (int[] edge : edges) {

            graph.computeIfAbsent(edge[0], integer -> new HashSet<>())
                    .add(edge[1]);

            graph.computeIfAbsent(edge[1], integer -> new HashSet<>())
                    .add(edge[0]);

            uf.union(edge[0], edge[1]);
        }

        int ans = 0;

        boolean[] visited = new boolean[n];

        OUTER:
        for (int i = 0; i < n; i++) {
            int parent = uf.getParent(i);

            if (visited[parent]) continue;

            visited[parent] = true;

            Set<Integer> adj = graph.get(i);

            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                if (!uf.isConnected(i, j)) continue;

                // 判断adj是否相同
                Set<Integer> adj2 = graph.get(j);

                if (adj.size() != adj2.size()) continue OUTER;

                for (Integer it : adj) {
                    if (it == j) continue;
                    if (!adj2.contains(it)) continue OUTER;
                }

            }

            ans++;

        }

        return ans;
    }


}
