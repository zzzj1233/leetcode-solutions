package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.*;

public class Leet924 {

    public static void main(String[] args) {

        System.out.println(minMalwareSpread(
                LeetUtils.convertInts("[[1,1,0],[1,1,0],[0,0,1]]"),
                new int[]{0, 1, 2}
        ));

        System.out.println(minMalwareSpread(
                LeetUtils.convertInts("[[1,0,0,0,1,0,0,0,0,0,1],[0,1,0,1,0,0,0,0,0,0,0],[0,0,1,0,0,0,0,1,0,0,0],[0,1,0,1,0,1,0,0,0,0,0],[1,0,0,0,1,0,0,0,0,0,0],[0,0,0,1,0,1,0,0,1,1,0],[0,0,0,0,0,0,1,1,0,0,0],[0,0,1,0,0,0,1,1,0,0,0],[0,0,0,0,0,1,0,0,1,0,0],[0,0,0,0,0,1,0,0,0,1,0],[1,0,0,0,0,0,0,0,0,0,1]]"),
                new int[]{7, 8, 6, 2, 3}
        ));
    }

    public static int minMalwareSpread(int[][] graph, int[] initial) {

        int ans = Integer.MAX_VALUE;
        int minCnt = Integer.MAX_VALUE;

        Arrays.sort(initial);

        Map<Integer, Set<Integer>> G = new HashMap<>();

        for (int i = 0; i < graph.length; i++) {

            for (int adj = 0; adj < graph[i].length; adj++) {
                if (graph[i][adj] == 1 && adj != i)
                    G.computeIfAbsent(i, integer -> new HashSet<>()).add(adj);

            }

        }

        for (int rm : initial) {
            boolean[] visited = new boolean[graph.length];
            int res = 0;
            for (int i : initial) {
                if (i == rm) continue;
                res += dfs(G, visited, i);
            }
            // System.out.println("rm = " + rm + " , res = " + res);
            if (res < minCnt) {
                minCnt = res;
                ans = rm;
            }
        }

        return ans;
    }

    public static int dfs(Map<Integer, Set<Integer>> graph, boolean[] visited, int cur) {

        if (visited[cur]) return 0;

        visited[cur] = true;

        Set<Integer> neigh = graph.get(cur);

        if (neigh == null)
            return 1;

        int res = 1;

        for (Integer i : neigh) {
            if (visited[i])
                continue;
            res += dfs(graph, visited, i);
        }

        return res;
    }

}
