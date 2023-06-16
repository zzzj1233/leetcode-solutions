package com.zzzj.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2023-06-12 16:17
 */
public class Leet2477 {

    static long ans;

    public static long minimumFuelCost(int[][] roads, int seats) {

        if (roads.length == 0) return 0;

        ans = 0;

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] road : roads) {
            graph.computeIfAbsent(road[0], integer -> new HashSet<>()).add(road[1]);
        }

        Set<Integer> adj = graph.get(0);

        if (adj == null) return 0;


        return -1;
    }

    public static int dfs(Map<Integer, Set<Integer>> graph, int seats, int cur) {

        Set<Integer> adj = graph.get(cur);

        if (adj == null) return 1;

        int subTreeSize = 0;

        for (Integer it : adj) {
            subTreeSize += dfs(graph, seats, it);
        }

        return -1;
    }

}
