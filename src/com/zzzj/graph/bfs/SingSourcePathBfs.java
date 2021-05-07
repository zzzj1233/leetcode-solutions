package com.zzzj.graph.bfs;

import com.zzzj.graph.Graph;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2021-05-02 12:47
 */
public class SingSourcePathBfs {

    private final Graph graph;

    private final boolean[] visited;

    private final int[] pre;

    private final int source;

    public SingSourcePathBfs(Graph graph, int source) {
        this.graph = graph;
        this.visited = new boolean[this.graph.getN()];
        this.pre = new int[this.graph.getN()];
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
                    visited[adj] = true;
                    pre[adj] = first;
                    queue.addLast(adj);
                }
            }

        }
    }

    private boolean isConnected(int target) {
        return visited[target];
    }

    private ArrayList<Integer> path(int target) {
        if (!isConnected(target)) {
            return null;
        }

        ArrayList<Integer> list = new ArrayList<>(graph.getE());

        int cur = target;

        while (cur != source) {
            list.add(cur);
            cur = pre[cur];
        }
        list.add(source);
        Collections.reverse(list);
        return list;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph("dfsg1");
        SingSourcePathBfs bfs = new SingSourcePathBfs(graph, 0);
        System.out.println(bfs.path(6));
    }

}
