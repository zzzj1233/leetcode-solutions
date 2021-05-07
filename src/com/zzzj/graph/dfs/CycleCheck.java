package com.zzzj.graph.dfs;

import com.zzzj.graph.Graph;

import java.io.FileNotFoundException;

/**
 * @author Zzzj
 * @create 2021-04-28 22:30
 */
// 检测一个图中是否有环
public class CycleCheck {

    private final Graph graph;

    private boolean[] visited;

    private boolean hasCycle = false;

    public CycleCheck(Graph graph) {
        this.graph = graph;

        visited = new boolean[graph.getN()];

        for (int i = 0; i < graph.getN(); i++) {
            if (!visited[i]) {
                if (dfs(i, i)) {
                    hasCycle = true;
                    return;
                }
            }
        }
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    private boolean dfs(int e, int previous) {
        visited[e] = true;
        for (int adj : graph.adj(e)) {
            if (!visited[adj]) {
                if (dfs(adj, e)) {
                    return true;
                }
            } else if (adj != previous) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph("dfsg1");
        CycleCheck cycleCheck = new CycleCheck(graph);

        System.out.println("g1 has cycle = " + cycleCheck.hasCycle);

        Graph graph2 = new Graph("dfsg2");
        CycleCheck cycleCheck2 = new CycleCheck(graph2);

        System.out.println("g2 has cycle = " + cycleCheck2.hasCycle);
    }

}
