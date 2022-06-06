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

    private final List<Integer>[] paths;

    public Dijkstra(WeightedGraph graph, int source) {
        this.graph = graph;
        this.source = source;
        this.distance = new int[graph.getN()];
        this.visited = new boolean[graph.getN()];
        Arrays.fill(distance, Integer.MAX_VALUE);

        // source -> source , distance = 0
        this.distance[source] = 0;
        this.visited[source] = true;

        this.initMinDistance();

        // 每个节点到每个节点的最短路径
        paths = new List[graph.getN()];
        this.initShortestPath();
    }

    public void initMinDistance() {

        // 1. 从source节点可以达到的所有节点,最短的距离就可以确定是到这个节点的最短距离
        int cur = source;
        while (true) {
            int curDistance = Integer.MAX_VALUE;
            int curNeighbor = -1;

            for (Map.Entry<Integer, Integer> adj : graph.adj(cur)) {
                Integer neighbor = adj.getKey();
                Integer distance = adj.getValue();
                if (!visited[neighbor] && distance < curDistance) {
                    curDistance = distance;
                    // 找到所有可达节点,距离最短的节点
                    curNeighbor = neighbor;
                }
            }

            // 跑完了
            if (curNeighbor == -1) {
                break;
            }

            visited[curNeighbor] = true;
            distance[curNeighbor] = curDistance + distance[cur];
            cur = curNeighbor;
        }
    }

    public void initShortestPath() {

        for (int i = 0; i < graph.getN(); i++) {

            // 终点就是source
            if (i == source) {
                paths[i] = Collections.emptyList();
                continue;
            }

            LinkedList<Integer> path = new LinkedList<>();

            paths[i] = path;

            int cur = source;

            boolean[] visited = new boolean[graph.getN()];

            while (true) {
                int curDistance = Integer.MAX_VALUE;
                int curNeighbor = -1;

                for (Map.Entry<Integer, Integer> adj : graph.adj(cur)) {
                    Integer neighbor = adj.getKey();
                    Integer distance = adj.getValue();
                    if (!visited[neighbor] && distance < curDistance) {
                        curDistance = distance;
                        // 找到所有可达节点,距离最短的节点
                        curNeighbor = neighbor;
                    }
                }

                path.add(cur);
                visited[curNeighbor] = true;

                // 找到终点了
                if (curNeighbor == i) {
                    break;
                }

                cur = curNeighbor;
            }

            path.add(i);
        }

    }

    public int[] getDistance() {
        return distance;
    }


    /**
     * 到target的最短路径
     */
    public List<Integer> shortestPath(int target) {
        return new ArrayList<>(paths[target]);
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

        System.out.println(Arrays.toString(dijkstra.getDistance()));

        System.out.println(dijkstra.shortestPath(1));
        System.out.println(dijkstra.shortestPath(4));
        System.out.println(dijkstra.shortestPath(3));
    }

}
