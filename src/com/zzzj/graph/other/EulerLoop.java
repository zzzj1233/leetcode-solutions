package com.zzzj.graph.other;

import com.zzzj.graph.Graph;
import com.zzzj.graph.dfs.CcCount;
import com.zzzj.leet.Leet102;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 * @author Zzzj
 * @create 2021-05-05 20:13
 */
public class EulerLoop {

    private final Graph graph;

    private final HashSet<Leet102.Pair<Integer, Integer>> visited;

    public EulerLoop(Graph graph) {
        this.graph = graph;
        visited = new HashSet<>(graph.getE(), 1);
        boolean hasEulerLoop = false;
        if (new CcCount(graph).getCccount() == 1) {
            hasEulerLoop = true;
            for (int i = 0; i < graph.getE(); i++) {
                if (graph.adj(i).size() % 2 != 0) {
                    hasEulerLoop = false;
                    break;
                }
            }
        }

        if (hasEulerLoop) {
            buildPath();
        }
    }

    private void buildPath() {

    }

    // hierholzer算法
    private void hierholzer() {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        ArrayList<Integer> ret = new ArrayList<>(graph.getE());
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            boolean remain = false;

            for (int adj : graph.adj(pop)) {
                // visited
                if (!visited.contains(new Leet102.Pair<>(pop, adj))) {
                    remain = true;
                    stack.push(adj);
                }
            }

            if (!remain) {
                ret.add(pop);
            }
        }

    }

    public void getPath() {

    }

}
