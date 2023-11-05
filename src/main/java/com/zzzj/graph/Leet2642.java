package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.*;

public class Leet2642 {

    public static void main(String[] args) {

        Graph graph = new Graph(4, LeetUtils.convertInts("[[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]]"));

        graph.addEdge(new int[]{1, 3, 4});

        System.out.println(graph.shortestPath(0, 3));

    }

    private static class Graph {

        private final int n;

        private final Map<Integer, Map<Integer, Integer>> G;

        public Graph(int n, int[][] edges) {
            this.n = n;
            G = new HashMap<>();
            for (int[] edge : edges) {
                G.computeIfAbsent(edge[0], integer -> new HashMap<>())
                        .put(edge[1], edge[2]);
            }
        }

        public void addEdge(int[] edge) {
            G.computeIfAbsent(edge[0], integer -> new HashMap<>())
                    .put(edge[1], edge[2]);
        }

        public int shortestPath(int node1, int node2) {

            int[] distance = new int[n + 1];

            Arrays.fill(distance, Integer.MAX_VALUE);

            boolean[] visited = new boolean[n + 1];

            distance[node1] = 0;

            while (true) {
                // find minNode

                int cur = -1;

                for (int i = 0; i <= n; i++) {
                    if (!visited[i] && (cur == -1 || distance[i] < distance[cur])) {
                        cur = i;
                    }
                }

                if (cur == -1 || distance[cur] == Integer.MAX_VALUE)
                    break;

                if (cur == node2)
                    break;

                visited[cur] = true;

                Map<Integer, Integer> adjs = G.get(cur);

                if (adjs == null)
                    continue;

                for (Map.Entry<Integer, Integer> entry : adjs.entrySet()) {

                    Integer node = entry.getKey();

                    Integer cost = entry.getValue();

                    if (visited[node]) continue;

                    distance[node] = Math.min(
                            distance[node],
                            distance[cur] + cost
                    );
                }

            }

            return distance[node2] == Integer.MAX_VALUE ? -1 : distance[node2];
        }

    }

}
