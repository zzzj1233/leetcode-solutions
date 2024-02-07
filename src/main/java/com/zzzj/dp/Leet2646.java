package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-08-21 12:18
 */
public class Leet2646 {

    public static void main(String[] args) {

        System.out.println(minimumTotalPrice(
                4,
                LeetUtils.convertInts("[[0,1],[1,2],[1,3]]"),
                LeetUtils.convertInts1("[2,2,10,6]"),
                LeetUtils.convertInts("[[0,3],[2,1],[2,3]]")
        ));

        System.out.println(minimumTotalPrice(
                9,
                LeetUtils.convertInts("[[2,5],[3,4],[4,1],[1,7],[6,7],[7,0],[0,5],[5,8]]"),
                LeetUtils.convertInts1("[4,4,6,4,2,4,2,14,8]"),
                LeetUtils.convertInts("[[1,5],[2,7],[4,3],[1,8],[2,8],[4,3],[1,5],[1,4],[2,1],[6,0],[0,7],[8,6],[4,0],[7,5],[7,5],[6,0],[5,1],[1,1],[7,5],[1,7],[8,7],[2,3],[4,1],[3,5],[2,5],[3,7],[0,1],[5,8],[5,3],[5,2]]")
        ));

    }

    public static int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], integer -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], integer -> new HashSet<>()).add(edge[0]);
        }

        int[] visitCnt = new int[n];

        for (int[] trip : trips)
            visit(trip[0], trip[1], -1, visitCnt, graph);

        int[] f = dfs(0, -1, visitCnt, price, graph);

        return Math.min(f[0], f[1]);
    }

    private static int[] dfs(
            int node,
            int parent,
            int[] visitCnt,
            int[] price,
            Map<Integer, Set<Integer>> graph
    ) {
        // 0 = 原价
        // 1 = 减半

        int[] f = new int[2];

        f[0] = price[node] * visitCnt[node];
        f[1] = (price[node] / 2) * visitCnt[node];

        for (Integer adj : graph.getOrDefault(node, Collections.emptySet())) {
            if (adj == parent)
                continue;
            int[] w = dfs(adj, node, visitCnt, price, graph);
            f[0] += Math.min(w[0], w[1]);
            f[1] += w[0];
        }

        return f;
    }

    private static boolean visit(
            int node,
            int end,
            int parent,
            int[] visitCnt,
            Map<Integer, Set<Integer>> graph
    ) {

        if (node == end) {
            visitCnt[node]++;
            return true;
        }

        for (Integer adj : graph.getOrDefault(node, Collections.emptySet())) {

            if (adj == parent)
                continue;

            if (visit(adj, end, node, visitCnt, graph)) {
                visitCnt[node]++;
                return true;
            }

        }

        return false;
    }
}
