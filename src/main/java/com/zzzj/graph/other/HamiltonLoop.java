package com.zzzj.graph.other;

import com.zzzj.graph.Graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2021-05-03 22:51
 */
// 求哈密尔顿回路
public class HamiltonLoop {

    private final Graph graph;

    private boolean[] visited;

    private int[] pre;

    private int end;

    private List<Integer> path;

    private static final int START = 0;

    public HamiltonLoop(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getN()];
        pre = new int[graph.getN()];
        path = new LinkedList<>();
        if (dfs(0, 1)) {
            buildPath();
        }
    }

    private void buildPath() {
        int cur = end;

        while (cur != START) {
            path.add(cur);
            cur = pre[cur];
        }

        path.add(START);
        Collections.reverse(path);
    }

    private boolean dfs(int e, int order) {
        visited[e] = true;

        if (order == graph.getN() && graph.adj(e).contains(START)) {
            end = e;
            return true;
        }

        for (int adj : graph.adj(e)) {
            if (!visited[adj]) {
                pre[adj] = e;
                if (dfs(adj, order + 1)) {
                    return true;
                }
            }
        }

        visited[e] = false;
        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("dfsg6");
        HamiltonLoop hamiltonLoop = new HamiltonLoop(graph);
        System.out.println(hamiltonLoop.path);
    }

}
