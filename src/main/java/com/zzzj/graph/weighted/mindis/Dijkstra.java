package com.zzzj.graph.weighted.mindis;

import com.zzzj.graph.weighted.WeightedGraph;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Zzzj
 * @create 2022-06-05 12:12
 */
public class Dijkstra {

    private final WeightedGraph graph;

    private final int source;

    private final boolean[] visited;

    private final int[] distance;

    public Dijkstra(WeightedGraph graph, int source) {
        this.graph = graph;
        this.source = source;
        this.visited = new boolean[graph.getN()];
        this.distance = new int[graph.getN()];
        this.calculateMinDistance();
    }

    private void calculateMinDistance() {

        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[source] = 0;

        while (true) {

            int min = Integer.MAX_VALUE;

            int minNode = -1;

            for (int node = 0; node < graph.getN(); node++) {
                if (!visited[node]) {
                    if (distance[node] < min) {
                        min = distance[node];
                        minNode = node;
                    }
                }
            }

            if (minNode == -1) {
                break;
            }

            visited[minNode] = true;

            for (Map.Entry<Integer, Integer> entry : graph.adj(minNode)) {

                Integer adj = entry.getKey();

                int dis = entry.getValue();

                if (!visited[adj])
                    distance[adj] = Math.min(distance[adj], dis + distance[minNode]);

            }

        }

    }

    public int distance(int v) {
        return distance[v];
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph(new Scanner("5 8\n" +
                "0 1 4\n" +
                "0 2 2\n" +
                "1 2 1\n" +
                "1 3 2\n" +
                "1 4 3\n" +
                "2 3 4\n" +
                "2 4 5\n" +
                "3 4 1"));

        int source = 0;

        Dijkstra dijkstra = new Dijkstra(g, 0);

        for (int i = 0; i < g.getN(); i++) {
            System.out.printf("%d -> %d = %d %n", source, i, dijkstra.distance(i));
        }

    }

}
