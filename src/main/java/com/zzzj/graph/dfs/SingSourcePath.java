package com.zzzj.graph.dfs;

import com.zzzj.graph.Graph;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Zzzj
 * @create 2021-04-28 21:42
 */
// 求解单源路径问题
public class SingSourcePath {

    private final Graph graph;

    private int[] visited;

    private int[] pre;

    public SingSourcePath(Graph graph) {
        this.graph = graph;

        this.pre = new int[graph.getN()];

        visited = new int[graph.getN()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }

        int ccIndex = 0;
        for (int i = 0; i < graph.getN(); i++) {
            if (visited[i] == -1) {
                dfs(i, i, ccIndex);
                ccIndex++;
            }
        }
    }

    private void dfs(int e, int previous, int ccIndex) {
        visited[e] = ccIndex;
        pre[e] = previous;
        for (int adj : graph.adj(e)) {
            if (visited[adj] == -1) {
                dfs(adj, e, ccIndex);
            }
        }
    }

    private boolean isConnected(int s, int t) {
        return visited[s] == visited[t];
    }

    public List<Integer> path(int source, int target) {
        if (!isConnected(source, target)) {
            return Collections.emptyList();
        }
        int cur = target;
        List<Integer> result = new ArrayList<>(10);
        while (cur != source) {
            result.add(cur);
            cur = pre[cur];
        }
        result.add(source);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph("dfsg1");
        SingSourcePath singSourcePath = new SingSourcePath(graph);

        System.out.println("0 - 5 : " + singSourcePath.path(0, 5));
        System.out.println("0 - 6 : " + singSourcePath.path(0, 6));
        System.out.println("0 - 4 : " + singSourcePath.path(0, 4));
    }

}
