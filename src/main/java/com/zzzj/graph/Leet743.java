package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-08-02 14:50
 */
public class Leet743 {


    public static void main(String[] args) {
        System.out.println(networkDelayTime(
                LeetUtils.convertInts("[[2,1,1],[2,3,1],[3,4,1]]"),
                4,
                2
        ));
    }


    public static int networkDelayTime(int[][] times, int n, int k) {

        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

        for (int[] time : times) {
            graph.computeIfAbsent(time[0], integer -> new HashMap<>())
                    .put(time[1], time[2]);
        }

        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);

        boolean[] visited = new boolean[n];

        int current = k;

        distance[current] = 0;
        visited[current] = true;

        while (current != -1) {

            int minDisAdj = -1;

            int minDis = Integer.MAX_VALUE;

            if (graph.containsKey(current)) {

                for (Map.Entry<Integer, Integer> entry : graph.get(current).entrySet()) {

                    Integer adj = entry.getKey();

                    Integer dis = entry.getValue();

                    if (visited[adj]) continue;

                    distance[adj] = Math.min(
                            distance[adj],
                            dis + distance[current]
                    );

                    if (distance[adj] < minDis) {
                        minDis = distance[adj];
                        minDisAdj = adj;
                    }
                }

            }

            if (minDisAdj != -1) {
                visited[minDisAdj] = true;
            }

            current = minDisAdj;
        }

        int max = Arrays.stream(distance).max().orElse(0);

        return max == Integer.MAX_VALUE ? -1 : max;
    }

}
