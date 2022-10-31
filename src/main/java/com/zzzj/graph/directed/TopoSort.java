package com.zzzj.graph.directed;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TopoSort {

    private final DirectedGraph graph;

    private List<Integer> sortedPath;

    public TopoSort(DirectedGraph graph) {
        this.graph = graph;
        this.initPath();
    }

    private void initPath() {
        sortedPath = new ArrayList<>(graph.getN());

        LinkedList<Integer> queue = new LinkedList<>();

        int[] inDegree = new int[graph.getN()];

        for (int i = 0; i < graph.getN(); i++) {
            if (graph.inDegree(i) == 0) {
                queue.add(i);
            }
            inDegree[i] = graph.inDegree(i);
        }

        while (!queue.isEmpty()) {
            Integer first = queue.removeFirst();
            sortedPath.add(first);
            List<Integer> neighbors = graph.adj(first);
            for (Integer adj : neighbors) {
                inDegree[adj]--;
                if (inDegree[adj] == 0) {
                    queue.addLast(adj);
                }
            }
        }

        if (sortedPath.size() != graph.getN()) {
            throw new RuntimeException("Detected cycle");
        }
    }

    public List<Integer> getSortedPath() {
        return sortedPath;
    }

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph("ug.txt");

        System.out.println(new TopoSort(graph).getSortedPath());
    }

}
