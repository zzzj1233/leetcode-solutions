package com.zzzj.graph.dfs;

import com.zzzj.graph.Graph;

import java.io.FileNotFoundException;

/**
 * @author Zzzj
 * @create 2021-04-28 21:17
 */
// 求解联通分量的个数
public class CcCount {

    private final Graph graph;

    private int cccount;

    private boolean visited[];

    public CcCount(Graph graph) {
        this.graph = graph;

        visited = new boolean[graph.getN()];

        for (int i = 0; i < graph.getN(); i++) {
            if (!visited[i]){
                dfs(i);
                cccount++;
            }
        }
    }


    private void dfs(int e) {
        visited[e] = true;
        for (int adj : graph.adj(e)) {
            if (!visited[adj]) {
                dfs(adj);
            }
        }
    }

    public int getCccount() {
        return cccount;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("cccount = " + new CcCount(new Graph("dfsg1")).getCccount());
    }

}
