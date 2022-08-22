package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-08-19 18:05
 */
public class Leet323 {


    public static void main(String[] args) {
        System.out.println(countComponents(5, LeetUtils.convertInts("[[0, 1], [1, 2], [3, 4]]")));
    }

    public static int countComponents(int n, int[][] edges) {

        // 建图
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], integer -> new ArrayList<>())
                    .add(edge[1]);
        }


        boolean[] visited = new boolean[n];

        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(visited, graph, i);
                ans++;
            }
        }

        return ans;
    }

    public static void dfs(boolean[] visited, Map<Integer, List<Integer>> graph, Integer key) {
        visited[key] = true;

        List<Integer> list = graph.get(key);

        if (list == null) {
            return;
        }

        for (Integer item : list) {
            if (!visited[item]) {
                dfs(visited, graph, item);
            }
        }
    }

}
