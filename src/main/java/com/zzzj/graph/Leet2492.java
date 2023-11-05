package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Leet2492 {

    public static void main(String[] args) {

//        System.out.println(minScore(4, LeetUtils.convertInts("[[1,2,2],[1,3,4],[3,4,7]]")));

        System.out.println(minScore(3, LeetUtils.convertInts("[[3,2,1],[1,3,3]]")));

    }

    static int min = Integer.MAX_VALUE;

    public static int minScore(int n, int[][] roads) {

        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

        for (int[] road : roads) {
            graph.computeIfAbsent(road[0], integer -> new HashMap<>()).put(road[1], road[2]);
            graph.computeIfAbsent(road[1], integer -> new HashMap<>()).put(road[0], road[2]);
        }

        min = Integer.MAX_VALUE;

        dfs(graph, new boolean[n + 1], 1, n);

        return min;
    }

    public static void dfs(Map<Integer, Map<Integer, Integer>> graph, boolean[] visited, int node, int n) {

        visited[node] = true;

        Map<Integer, Integer> adjs = graph.getOrDefault(node, Collections.emptyMap());

        for (Map.Entry<Integer, Integer> entry : adjs.entrySet()) {

            Integer next = entry.getKey();

            Integer cost = entry.getValue();

            if (visited[next] && min <= cost)
                continue;

            min = Math.min(min, cost);

            dfs(graph, visited, next, n);
        }

    }

}
