package com.zzzj.graph.bfs;

import com.zzzj.graph.Graph;

import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2021-05-02 13:35
 */

// 求target-source的最短路径长度
public class USSSBfs {

    private final Graph graph;

    private final boolean[] visited;

    private final int[] dis;

    private final int source;

    public USSSBfs(Graph graph, int source) {
        this.graph = graph;
        this.visited = new boolean[this.graph.getN()];
        this.dis = new int[this.graph.getN()];
        this.source = source;
        bfs(this.source);
    }

    private void bfs(int e) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(e);

        while (!queue.isEmpty()) {
            int first = queue.pollFirst();
            visited[first] = true;
            for (int adj : graph.adj(first)) {
                if (!visited[adj]) {
                    dis[adj] = dis[first] + 1;
                    visited[adj] = true;
                    queue.addLast(adj);
                }
            }
        }
    }

    public int distance(int target) {
        return dis[target];
    }

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph("dfsg1");
        USSSBfs bfs = new USSSBfs(graph, 0);
        System.out.println(" 0 ~ 6 : " + bfs.distance(6));
        System.out.println(" 0 ~ 3 : " + bfs.distance(3));
        System.out.println(" 0 ~ 2 : " + bfs.distance(2));
    }

}
