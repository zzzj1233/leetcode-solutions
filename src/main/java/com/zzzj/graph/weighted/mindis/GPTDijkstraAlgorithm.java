package com.zzzj.graph.weighted.mindis;


import java.util.*;

public class GPTDijkstraAlgorithm {

    // 图的邻接表表示
    private Map<Integer, List<Edge>> graph;

    private static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public GPTDijkstraAlgorithm() {
        graph = new HashMap<>();
    }

    public void addEdge(int source, int destination, int weight) {
        graph.putIfAbsent(source, new ArrayList<>());
        graph.putIfAbsent(destination, new ArrayList<>());
        graph.get(source).add(new Edge(destination, weight));
        graph.get(destination).add(new Edge(source, weight)); // 无向图需要添加双向边
    }

    public Map<Integer, Integer> shortestPath(int source) {
        Map<Integer, Integer> distanceMap = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] currNode = pq.poll();
            int currVertex = currNode[0];
            int currDistance = currNode[1];

            // Skip if the distance is already found from another path
            if (distanceMap.containsKey(currVertex)) {
                continue;
            }

            distanceMap.put(currVertex, currDistance);

            if (graph.containsKey(currVertex)) {
                for (Edge neighbor : graph.get(currVertex)) {
                    int nextVertex = neighbor.destination;
                    int nextDistance = currDistance + neighbor.weight;
                    pq.offer(new int[]{nextVertex, nextDistance});
                }
            }
        }

        return distanceMap;
    }

    public static void main(String[] args) {
        GPTDijkstraAlgorithm dijkstra = new GPTDijkstraAlgorithm();
        dijkstra.addEdge(0, 1, 4);
        dijkstra.addEdge(0, 2, 2);
        dijkstra.addEdge(1, 2, 1);
        dijkstra.addEdge(1, 3, 2);
        dijkstra.addEdge(1, 4, 3);
        dijkstra.addEdge(2, 3, 4);
        dijkstra.addEdge(2, 4, 5);
        dijkstra.addEdge(3, 4, 1);

        int sourceNode = 0;
        Map<Integer, Integer> shortestDistances = dijkstra.shortestPath(sourceNode);

        System.out.println(shortestDistances);
    }
}
