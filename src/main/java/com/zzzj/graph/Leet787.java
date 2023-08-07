package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.Unresolved;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-08-02 12:57
 */
@Unresolved
public class Leet787 {

    public static void main(String[] args) {
        System.out.println(findCheapestPrice(3, LeetUtils.convertInts("[[0,1,100],[1,2,100],[0,2,500]]"), 0, 2, 1));

        System.out.println(findCheapestPrice(3, LeetUtils.convertInts("[[0,1,100],[1,2,100],[0,2,500]]"), 0, 2, 0));
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

        for (int[] flight : flights) {
            graph.computeIfAbsent(flight[0], integer -> new HashMap<>())
                    .put(flight[1], flight[2]);

            graph.computeIfAbsent(flight[1], integer -> new HashMap<>())
                    .put(flight[0], flight[2]);
        }

        int[] distance = new int[n];

        boolean[] visited = new boolean[n];

        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[src] = 0;

        visited[src] = true;

        int current = src;

        while (current != -1 && k >= 0) {

            int minDistance = Integer.MAX_VALUE;

            int minDistanceAdj = -1;

            for (Map.Entry<Integer, Integer> entry : graph.get(current).entrySet()) {

                Integer adj = entry.getKey();

                Integer dis = entry.getValue();

                if (visited[adj]) continue;

                distance[adj] = Math.min(
                        distance[adj],
                        distance[current] + dis
                );

                if (distance[adj] < minDistance) {
                    minDistance = distance[adj];
                    minDistanceAdj = adj;
                }

            }

            if (minDistanceAdj != -1) {
                visited[minDistanceAdj] = true;
            }

            current = minDistanceAdj;

            if (minDistanceAdj == dst) return minDistance;

            k--;
        }

        return distance[dst] == Integer.MAX_VALUE ? -1 : distance[dst];
    }

}
