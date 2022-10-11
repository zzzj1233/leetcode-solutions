package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-10-10 17:48
 */
public class Leet2368 {


    public static void main(String[] args) {
        System.out.println(reachableNodes(7, LeetUtils.convertInts("[[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]]"), new int[]{4, 5}));
    }

    public static int reachableNodes(int n, int[][] edges, int[] restricted) {
        // 建图

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], integer -> new HashSet<>())
                    .add(edge[1]);

            graph.computeIfAbsent(edge[1], integer -> new HashSet<>())
                    .add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>(n);

        for (int i : restricted) {
            visited.add(i);
        }

        return dfs(graph, 0, visited);
    }

    public static int dfs(Map<Integer, Set<Integer>> graph, int cur, Set<Integer> visited) {
        if (visited.contains(cur)) {
            return 0;
        }
        Set<Integer> neigh = graph.get(cur);

        int result = 1;

        visited.add(cur);

        if (neigh != null) {
            for (Integer it : neigh) {
                result += dfs(graph, it, visited);
            }
        }

        return result;
    }

}
