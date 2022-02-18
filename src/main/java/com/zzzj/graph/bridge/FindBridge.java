package com.zzzj.graph.bridge;

import com.zzzj.graph.Graph;
import com.zzzj.leet.Leet102;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2021-05-03 20:58
 */
public class FindBridge {

    private final Graph graph;

    private boolean[] visited;

    private int[] order;

    private int[] low;

    private List<Leet102.Pair<Integer, Integer>> bridge;

    private boolean isBinaryGraph = true;

    public FindBridge(Graph graph) {
        this.graph = graph;

        visited = new boolean[graph.getN()];

        order = new int[graph.getN()];
        low = new int[graph.getN()];

        bridge = new LinkedList<>();

        for (int i = 0; i < graph.getN(); i++) {
            if (!visited[i]) {
                dfs(i, i);
            }
        }
    }

    private int dfs(int e, int previous) {
        visited[e] = true;
        // order表示当前遍历的是第几个元素
        int curOrder = order[previous] + e;
        order[e] = curOrder;
        low[e] = curOrder;

        for (int adj : graph.adj(e)) {
            if (adj == previous) {
                continue;
            }
            if (visited[adj]) {
                low[e] = Math.min(low[e], order[adj]);
            } else {
                int dfsLow = dfs(adj, e);
                // 桥
                if (dfsLow > curOrder) {
                    bridge.add(new Leet102.Pair<>(e, adj));
                }
                low[e] = Math.min(low[e], dfsLow);
            }
        }

        return low[e];
    }

    public static void main(String[] args) {
        Graph graph = new Graph("dfsg5");
        FindBridge findBridge = new FindBridge(graph);

        System.out.println(findBridge.bridge);
    }
}
