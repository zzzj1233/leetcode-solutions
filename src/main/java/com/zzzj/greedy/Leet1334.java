package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Leet1334 {

    public static void main(String[] args) {

        System.out.println(findTheCity(4, LeetUtils.convertInts("[[0,1,3],[1,2,1],[1,3,4],[2,3,1]]"), 4));

    }

    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {

        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], integer -> new HashMap<>()).put(edge[1], edge[2]);
            graph.computeIfAbsent(edge[1], integer -> new HashMap<>()).put(edge[0], edge[2]);
        }

        int minCnt = Integer.MAX_VALUE;
        int minNode = -1;

        for (int i = 0; i < n; i++) {
            int res = shortestPath(n, i, graph, distanceThreshold);

            if (res <= minCnt) {
                minCnt = res;
                minNode = i;
            }
        }

        return minNode;
    }

    public static int shortestPath(int n, int node, Map<Integer, Map<Integer, Integer>> graph, int distanceThreshold) {

        boolean[] visited = new boolean[n];

        int[] distance = new int[n];

        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[node] = 0;

        int cnt = 0;

        while (true) {

            int cur = -1;

            // findMin
            for (int i = 0; i < n; i++) {
                if (!visited[i] && (cur == -1 || distance[i] < distance[cur]))
                    cur = i;
            }

            if (cur == -1)
                break;

            if (distance[cur] > distanceThreshold)
                break;

            cnt++;

            visited[cur] = true;

            Map<Integer, Integer> adjs = graph.getOrDefault(cur, Collections.emptyMap());

            for (Map.Entry<Integer, Integer> entry : adjs.entrySet()) {

                Integer next = entry.getKey();

                Integer cost = entry.getValue();

                if (visited[next]) continue;

                distance[next] = Math.min(
                        distance[next],
                        distance[cur] + cost
                );
            }

        }

        return cnt;
    }
}
