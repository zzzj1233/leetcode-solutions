package com.zzzj.graph.leetcode;

/**
 * @author Zzzj
 * @create 2021-05-02 14:15
 */

public class IsBipartite {

    private int[][] graph;

    private int[] colors;

    private boolean[] visited;

    public boolean isBipartite(int[][] graph) {
        this.graph = graph;
        this.colors = new int[graph.length];
        this.visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                if (!dfs(i, i)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(int e, int pre) {

        for (int adj : graph[e]) {
            colors[e] = colors[pre] == 0 ? 1 : 0;
            visited[e] = true;
            if (colors[adj] == colors[adj]) {
                return false;
            }
            if (!visited[adj]) {
                if (!dfs(adj, e)) {
                    return false;
                }
            }
        }

        return true;
    }

}
