package com.zzzj.graph.weighted.mindis;

import com.zzzj.graph.weighted.WeightedGraph;

import java.io.StringReader;
import java.util.*;

/**
 * Dijkstra算法：求有权图的最短路径,不支持负权重的图
 *
 * @author Zzzj
 * @create 2022-06-05 12:12
 */
public class Dijkstra {

    private final WeightedGraph graph;

    private final int source;

    private final int[] distance;

    private final boolean[] visited;

    public Dijkstra(WeightedGraph graph, int source) {

        this.graph = graph;

        this.source = source;

        this.distance = new int[graph.getN()];

        this.visited = new boolean[graph.getN()];

        Arrays.fill(distance, Integer.MAX_VALUE);
    }

    public void initMinDistance() {

        distance[source] = 0;

        int cur = source;

        while (cur != -1) {
            int min = Integer.MAX_VALUE;
            int minNeigh = -1;
            visited[cur] = true;

            for (Map.Entry<Integer, Integer> neigh : graph.adj(cur)) {

                int neighNode = neigh.getKey();

                int cost = neigh.getValue();

                if (visited[neighNode])
                    continue;

                distance[neighNode] = Math.min(
                        distance[neighNode],
                        distance[cur] + cost
                );

                if (distance[neighNode] < min) {
                    min = distance[neighNode];
                    minNeigh = neighNode;
                }

            }

            cur = minNeigh;
        }

    }

    public int[] getDistance() {
        return distance;
    }

    public int shortestDistance(int target) {
        return distance[target];
    }

    public static void main(String[] args) {
        String source = "5 8\n" +
                "0 1 4\n" +
                "0 2 2\n" +
                "1 2 1\n" +
                "1 3 2\n" +
                "1 4 3\n" +
                "2 3 4\n" +
                "2 4 5\n" +
                "3 4 1";

        WeightedGraph graph = new WeightedGraph(new StringReader(source));

        Dijkstra dijkstra = new Dijkstra(graph, 0);

        dijkstra.initMinDistance();

        System.out.println(Arrays.toString(dijkstra.getDistance()));

        // 3
        System.out.println(dijkstra.shortestDistance(1));
        // 2
        System.out.println(dijkstra.shortestDistance(2));
        // 5
        System.out.println(dijkstra.shortestDistance(3));
        // 6
        System.out.println(dijkstra.shortestDistance(4));
    }

}
