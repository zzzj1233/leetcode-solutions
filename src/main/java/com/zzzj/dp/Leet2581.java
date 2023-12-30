package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.*;

public class Leet2581 {

    public static void main(String[] args) {

        System.out.println(rootCount(LeetUtils.convertInts("[[0,1],[1,2],[1,3],[4,2]]"), LeetUtils.convertInts("[[1,3],[0,1],[1,0],[2,4]]"), 3));

    }

    public static int rootCount(int[][] edges, int[][] guesses, int k) {

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], integer -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], integer -> new HashSet<>()).add(edge[0]);
        }

        Map<Integer, Set<Integer>> g = new HashMap<>();

        for (int[] guess : guesses)
            g.computeIfAbsent(guess[0], integer -> new HashSet<>()).add(guess[1]);

        int cnt = dfs(0, -1, graph, g);

        return reRoot(0, -1, graph, g, cnt, k);
    }

    private static int reRoot(
            int i,
            int fa,
            Map<Integer, Set<Integer>> graph,
            Map<Integer, Set<Integer>> g,
            int cnt,
            int k
    ) {

        cnt -= g.getOrDefault(fa, Collections.emptySet()).contains(i) ? 1 : 0;
        cnt += g.getOrDefault(i, Collections.emptySet()).contains(fa) ? 1 : 0;

        int res = cnt >= k ? 1 : 0;

        for (Integer child : graph.get(i)) {

            if (child == fa)
                continue;

            res += reRoot(child, i, graph, g, cnt, k);
        }

        return res;
    }

    private static int dfs(
            int i,
            int fa,
            Map<Integer, Set<Integer>> graph,
            Map<Integer, Set<Integer>> g
    ) {

        int res = 0;

        for (Integer child : graph.get(i)) {

            if (child == fa)
                continue;

            res += g.getOrDefault(i, Collections.emptySet()).contains(child) ? 1 : 0;

            res += dfs(child, i, graph, g);
        }

        return res;
    }

}
