package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leet2316 {

    public static void main(String[] args) {

//        System.out.println(countPairs(7, LeetUtils.convertInts("[[0,2],[0,5],[2,4],[1,6],[5,4]]")));

        System.out.println(countPairs(5, LeetUtils.convertInts("[[1,0],[3,1],[0,4],[2,1]]")));

    }

    static int visitedCnt;

    public static long countPairs(int n, int[][] edges) {

        visitedCnt = 0;

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] edge : edges){
            graph.computeIfAbsent(edge[0], integer -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], integer -> new HashSet<>()).add(edge[0]);
        }

        boolean[] visited = new boolean[n];

        long ans = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            int cnt = dfs(i, visited, graph);
            ans += (long) cnt * (n - visitedCnt);
        }

        return ans;
    }

    public static int dfs(int node, boolean[] visited, Map<Integer, Set<Integer>> graph) {

        visited[node] = true;

        visitedCnt++;

        Set<Integer> adjs = graph.get(node);

        if (adjs == null)
            return 1;

        int cnt = 1;

        for (Integer adj : adjs) {
            if (visited[adj])
                continue;
            cnt += dfs(adj, visited, graph);
        }

        return cnt;
    }
}
