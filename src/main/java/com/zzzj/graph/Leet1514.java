package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.*;

public class Leet1514 {

    public static void main(String[] args) {

        System.out.println(maxProbability(
                3,
                LeetUtils.convertInts("[[0,1],[1,2],[0,2]]"),
                new double[]{0.5, 0.5, 0.2},
                0,
                2
        ));

    }

    public static double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {

        Map<Integer, Map<Integer, Double>> graph = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {

            int[] edge = edges[i];

            graph.computeIfAbsent(edge[0], integer -> new HashMap<>()).put(edge[1], succProb[i]);
            graph.computeIfAbsent(edge[1], integer -> new HashMap<>()).put(edge[0], succProb[i]);
        }

        double[] distance = new double[n];
        Arrays.fill(distance, Double.MIN_VALUE);
        distance[start_node] = 1;

        boolean[] visited = new boolean[n];

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start_node, 1));

        while (!queue.isEmpty()) {

            Node node = queue.remove();

            int cur = node.node;

            visited[cur] = true;

            Map<Integer, Double> adj = graph.getOrDefault(cur, Collections.emptyMap());

            for (Map.Entry<Integer, Double> entry : adj.entrySet()) {

                Integer next = entry.getKey();

                Double cost = entry.getValue();

                if (visited[next]) continue;

                double newDis = distance[cur] * cost;

                if (newDis > distance[next]) {
                    queue.add(new Node(next, newDis));
                    distance[next] = newDis;
                }

            }

        }

        return distance[end_node] == Double.MAX_VALUE ? 0 : distance[end_node];
    }

    private static class Node implements Comparable<Node> {
        int node;
        double prob;

        @Override
        public int compareTo(Node o) {
            return -Double.compare(prob, o.prob);
        }

        public Node(int node, double prob) {
            this.node = node;
            this.prob = prob;
        }
    }

}
