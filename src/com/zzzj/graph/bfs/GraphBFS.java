package com.zzzj.graph.bfs;

import com.zzzj.graph.Graph;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2021-05-02 12:29
 */
public class GraphBFS {

    private final Graph graph;

    private final boolean[] visited;

    // 遍历的节点数组
    private final ArrayList<Integer> order;

    public GraphBFS(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[this.graph.getN()];
        this.order = new ArrayList<>(this.graph.getN());
        for (int i = 0; i < this.graph.getN(); i++) {
            if (!visited[i]) {
                bfs(i);
            }
        }
    }

    private void bfs(int e) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(e);

        while (!queue.isEmpty()) {
            Integer first = queue.pollFirst();
            order.add(first);
            visited[first] = true;
            for (int adj : graph.adj(first)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    queue.addLast(adj);
                }
            }
        }
    }

    public ArrayList<Integer> getOrder() {
        return order;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph("dfsg1");
        GraphBFS bfs = new GraphBFS(graph);
        System.out.println(bfs.getOrder());
    }
}
