package com.zzzj.contest.week370;

import com.zzzj.leet.LeetUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q3 {

    public static void main(String[] args) {

        System.out.println(maximumScoreAfterOperations(
                LeetUtils.convertInts("[[0,1],[0,2],[0,3],[2,4],[4,5]]"),
                new int[]{5, 2, 5, 2, 1, 1}
        ));

        System.out.println(maximumScoreAfterOperations(
                LeetUtils.convertInts("[[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]"),
                new int[]{20, 10, 9, 7, 4, 3, 5}
        ));

        System.out.println(maximumScoreAfterOperations(
                LeetUtils.convertInts("[[0,1]]"),
                new int[]{1, 2}
        ));

    }


    public static long maximumScoreAfterOperations(int[][] edges, int[] values) {

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], integer -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], integer -> new HashSet<>()).add(edge[0]);
        }

        return dfs(0, graph, values, -1)[0];
    }

    private static long[] dfs(int node, Map<Integer, Set<Integer>> graph, int[] values, int parent) {

        Set<Integer> neigh = graph.get(node);

        if (neigh.size() == 1 && neigh.iterator().next() == parent)
            return new long[]{0, values[node]};

        long dpSum = 0;

        long sum = 0;

        for (Integer child : neigh) {
            if (child == parent)
                continue;

            long[] res = dfs(child, graph, values, node);
            dpSum += res[0];
            sum += res[1];
        }

        return new long[]{
                Math.max(sum, dpSum + values[node]),
                sum + values[node]
        };
    }


}
