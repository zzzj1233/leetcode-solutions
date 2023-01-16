package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2023-01-06 16:56
 */
public class Leet261 {

    public static void main(String[] args) {
        System.out.println(validTree(3, LeetUtils.convertInts("[[1,0],[2,0]]")));
    }

    // 1. 是连通图
    // 2. 不存在环
    public static boolean validTree(int n, int[][] edges) {
        if (edges.length == 0) {
            return n == 1;
        }

        Map<Integer, Set<Integer>> graph = buildGraph(edges);

        boolean[] visited = new boolean[n];

        visited[edges[0][0]] = true;

        return dfs(graph, edges[0][0], visited, -1) && allVisited(visited);
    }

    public static boolean allVisited(boolean[] visited) {
        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public static boolean dfs(Map<Integer, Set<Integer>> graph, int current, boolean[] visited, int previous) {
        Set<Integer> neigh = graph.get(current);

        if (neigh != null) {

            for (Integer item : neigh) {

                if (visited[item] && item != previous) {
                    return false;
                }

                if (visited[item]){
                    continue;
                }

                visited[item] = true;

                if (!dfs(graph, item, visited, current)) {
                    return false;
                }

            }
        }

        return true;
    }

    public static Map<Integer, Set<Integer>> buildGraph(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], integer -> new HashSet<>())
                    .add(edge[1]);

            graph.computeIfAbsent(edge[1], integer -> new HashSet<>())
                    .add(edge[0]);
        }

        return graph;
    }


}
