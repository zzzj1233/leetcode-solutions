package com.zzzj.graph.weighted.mindis;

import com.zzzj.graph.weighted.WeightedGraph;
import jdk.nashorn.api.scripting.AbstractJSObject;
import org.omg.CORBA.AnySeqHelper;

import java.io.StringReader;
import java.util.*;

public class Dijkstra2 {

    private final WeightedGraph graph;

    private final int source;

    private final int[] distance;

    private final boolean[] visited;

    public Dijkstra2(WeightedGraph graph, int source) {
        this.graph = graph;
        this.source = source;
        this.visited = new boolean[graph.getN()];
        this.distance = new int[graph.getN()];

        this.calcShortestPath();
    }

    public void calcShortestPath() {

        Arrays.fill(distance, Integer.MAX_VALUE);

        // source -> source 最短路径为0
        distance[source] = 0;
        visited[source] = true;

        int current = source;

        while (current != -1) {

            int minDistanceAdj = -1;

            int minDistance = Integer.MAX_VALUE;

            for (Map.Entry<Integer, Integer> entry : graph.adj(current)) {

                int adj = entry.getKey();

                int dis = entry.getValue();

                // 更新distance
                if (visited[adj]) continue;

                distance[adj] = Math.min(
                        distance[adj],
                        dis + distance[current]
                );

                if (distance[adj] < minDistance) {
                    minDistanceAdj = adj;
                    minDistance = distance[adj];
                }

            }

            // minDistanceAdj的路径已经求解得出
            // 因为current的adj里面, 最近的就是minDistanceAdj
            // 所以minDistanceAdj的最短路径一定是current到minDistanceAdj

            // 那么minDistanceAdj到source的最短路径就一定是 distance[current] + dis[current -> minDistanceAdj]
            if (minDistanceAdj != -1)
                visited[minDistanceAdj] = true;

            current = minDistanceAdj;
        }
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

        Dijkstra2 dijkstra = new Dijkstra2(graph, 0);

        System.out.println(dijkstra.shortestDistance(1));
        System.out.println(dijkstra.shortestDistance(4));
        System.out.println(dijkstra.shortestDistance(3));
    }

}
