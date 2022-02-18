package com.zzzj.backtracking;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-02-11 14:12
 */
public class Leet797 {

    public static void main(String[] args) {
        System.out.println(allPathsSourceTarget(new int[][]{{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}}));
    }

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if (graph.length == 0){
            return Collections.emptyList();
        }

        List<List<Integer>> ans = new ArrayList<>();

        int end = graph.length - 1;

        process(ans, end, 0, graph, new LinkedList<>());

        return ans;
    }

    public static void process(List<List<Integer>> ans, int end, int cur, int[][] graph, LinkedList<Integer> path) {
        if (cur == end) {
            path.add(cur);
            ans.add(new ArrayList<>(path));
            path.removeLast();
            return;
        }


        int[] routes = graph[cur];

        for (int route : routes) {
            path.add(cur);
            process(ans, end, route, graph, path);
            path.removeLast();
        }

    }

}
