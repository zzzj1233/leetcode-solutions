package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2023-06-12 16:17
 */
public class Leet2477 {

    public static void main(String[] args) {

        System.out.println(minimumFuelCost(LeetUtils.convertInts("[[0,1],[1,2],[1,3]]"), 2));

        System.out.println(minimumFuelCost(LeetUtils.convertInts("[[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]]"), 2));

        System.out.println(minimumFuelCost(LeetUtils.convertInts("[[0,1],[0,2],[0,3]]"), 5));

    }

    public static long minimumFuelCost(int[][] roads, int seats) {

        if (roads.length == 0)
            return 0;

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] road : roads) {
            graph.computeIfAbsent(road[0], integer -> new HashSet<>()).add(road[1]);
            graph.computeIfAbsent(road[1], integer -> new HashSet<>()).add(road[0]);
        }

        return dfs(0, -1, seats, graph)[0];
    }

    private static long[] dfs(
            int node,
            int parent,
            int seats,
            Map<Integer, Set<Integer>> graph
    ) {

        Set<Integer> adj = graph.get(node);

        // 根节点
        long[] w = new long[2];

        for (Integer neigh : adj) {

            if (neigh == parent)
                continue;

            long[] v = dfs(neigh, node, seats, graph);

            w[0] += v[0];
            w[1] += v[1];
        }

        w[1] += 1;

        if (node != 0) {
            w[0] += w[1] % seats == 0 ? w[1] / seats : (w[1] / seats + 1);
        }

        return w;
    }


}
