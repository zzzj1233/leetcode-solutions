package com.zzzj.graph.dfs;

import com.zzzj.graph.Graph;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2021-04-28 22:47
 */
// 检测一个图是否是二分图
public class BinaryGraphCheck {

    private final Graph graph;

    private boolean[] visited;

    private int[] colors;

    private boolean isBinaryGraph = true;

    public BinaryGraphCheck(Graph graph) {
        this.graph = graph;

        visited = new boolean[graph.getN()];

        colors = new int[graph.getN()];

        Arrays.fill(colors, -1);

        for (int i = 0; i < graph.getN(); i++) {
            if (!visited[i]) {
                if (!dfs(i, 1)) {
                    isBinaryGraph = false;
                    return;
                }
            }
        }
    }

    private boolean dfs(int e, int color) {
        visited[e] = true;
        colors[e] = color;
        for (int adj : graph.adj(e)) {
            if (colors[adj] == color) {
                return false;
            }
            if (!visited[adj]) {
                if (!dfs(adj, 1 - color)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph("dfsg1");
        BinaryGraphCheck check = new BinaryGraphCheck(graph);

        System.out.println("g1 is binaryGraph = " + check.isBinaryGraph);

        Graph graph3 = new Graph("dfsg3");
        BinaryGraphCheck check3 = new BinaryGraphCheck(graph3);

        System.out.println("g3 is binaryGraph = " + check3.isBinaryGraph);
    }

}
