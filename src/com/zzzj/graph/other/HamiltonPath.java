package com.zzzj.graph.other;

import com.zzzj.graph.Graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2021-05-03 22:51
 */
// 求哈密尔顿路径
public class HamiltonPath {

    private final Graph graph;

    private boolean[] visited;

    private int source;

    private int target;

    private static final int START = 0;

    public HamiltonPath(Graph graph, int source, int target) {
        this.graph = graph;
        this.source = source;
        this.target = target;
        visited = new boolean[graph.getN()];
        dfs(0, 0);
    }

    private boolean dfs(int e, int order) {
        // 走完了
        if (e == target && order == graph.getN()) {
            return true;
        }

        for (int adj : graph.adj(e)) {
            if (!visited[adj]) {
                if (dfs(adj, order + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("dfsg6");
    }

}
