package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-11-07 11:17
 */
public class Leet1377 {

    public static void main(String[] args) {

//        System.out.println(frogPosition(
//                7,
//                LeetUtils.convertInts("[[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]]"),
//                2,
//                4
//        ));

        System.out.println(frogPosition(
                8,
                LeetUtils.convertInts("[[2,1],[3,2],[4,1],[5,1],[6,4],[7,1],[8,7]]"),
                7,
                7
        ));

        System.out.println(frogPosition(
                7,
                LeetUtils.convertInts("[[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]]"),
                20,
                6
        ));

    }

    public static double frogPosition(int n, int[][] edges, int t, int target) {

        Map<Integer, Set<Integer>> graph = new HashMap<>(n);

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], integer -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], integer -> new HashSet<>()).add(edge[0]);
        }

        return bfs(n, graph, t, target);
    }

    private static double bfs(int n, Map<Integer, Set<Integer>> graph, int time, int target) {

        LinkedList<double[]> queue = new LinkedList<>();

        queue.add(new double[]{1, -1, 1});

        boolean[] visited = new boolean[n + 1];

        while (!queue.isEmpty() && time >= 0) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                double[] rm = queue.removeFirst();

                int node = (int) rm[0];

                int prev = (int) rm[1];

                visited[node] = true;

                double probability = rm[2];

                Set<Integer> neigh = graph.getOrDefault(node, Collections.emptySet());

                if (node == target)
                    return time == 0 || neigh.stream().allMatch(adj -> visited[adj]) ? probability : 0;

                int nextSize = neigh.size() - (node == 1 ? 0 : 1);

                for (Integer adj : neigh) {
                    if (visited[adj])
                        continue;
                    queue.add(new double[]{adj, node, probability / nextSize});
                }

            }

            time--;
        }

        return 0;
    }

}
