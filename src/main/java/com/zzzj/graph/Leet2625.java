package com.zzzj.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-19 15:37
 */
public class Leet2625 {

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int N = graph.length - 1;
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, new LinkedList<>(), 0, N, graph);
        return ans;
    }

    public static void dfs(List<List<Integer>> ans, LinkedList<Integer> path, int cur, int N, int[][] graph) {
        if (cur == N) {
            List<Integer> list = new ArrayList<>(path);
            list.add(N);
            ans.add(list);
            return;
        }

        path.add(cur);
        for (int next : graph[cur]) {
            dfs(ans, path, next, N, graph);
        }
        path.removeLast();
    }
}
