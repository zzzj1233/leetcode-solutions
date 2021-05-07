package com.zzzj.graph.dfs;

import com.zzzj.graph.Graph;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2021-04-28 21:29
 */
// 求一个图中每个联通分量的元素
public class ConnectedItems {

    private final Graph graph;

    private int cccount;

    private boolean visited[];

    private List<Integer>[] elements;

    public ConnectedItems(Graph graph) {
        this.graph = graph;

        visited = new boolean[graph.getN()];

        elements = new List[graph.getN()];
        for (int i = 0; i < graph.getN(); i++) {
            elements[i] = new ArrayList<>(4);
        }

        for (int i = 0; i < graph.getN(); i++) {
            if (!visited[i]) {
                dfs(i, cccount);
                cccount++;
            }
        }
    }

    private void dfs(int e, int ccIndex) {
        visited[e] = true;
        elements[cccount].add(e);
        for (int adj : graph.adj(e)) {
            if (!visited[adj]) {
                dfs(adj, ccIndex);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ConnectedItems connectedItems = new ConnectedItems(new Graph("dfsg1"));

        // elements = [0, 1, 3, 2, 6, 4]
        // elements = [5]
        for (List<Integer> element : connectedItems.elements) {
            if (!element.isEmpty()) {
                System.out.println("elements = " + element);
            }
        }
    }

}
