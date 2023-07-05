package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-06-29 16:26
 */
public class Leet847 {

    public static void main(String[] args) {

//        System.out.println(shortestPathLength(LeetUtils.convertInts("[[1,2,3],[0],[0],[0]]")));
//
//        System.out.println(shortestPathLength(LeetUtils.convertInts("[[1],[0,2,4],[1,3,4],[2],[1,2]]")));

        System.out.println(shortestPathLength(LeetUtils.convertInts("[[1],[0,2,4],[1,3],[2],[1,5],[4]]")));

    }

    public static int shortestPathLength(int[][] graph) {

        if (Arrays.stream(graph)
                .allMatch(arr -> arr.length == 0)) return 0;

        int N = graph.length;

        boolean[][] visited = new boolean[N][1 << N];

        LinkedList<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) queue.add(new int[]{i, 0, 0});

        final int expect = (1 << N) - 1;

        while (!queue.isEmpty()) {

            int[] item = queue.removeFirst();

            int index = item[0];

            int stat = item[1];

            int step = item[2];

            stat |= (1 << index);

            if (stat == expect) return step;

            for (int adj : graph[index]) {

                if (visited[index][stat | (1 << adj)]) continue;

                visited[index][stat | (1 << adj)] = true;

                queue.add(new int[]{adj, stat, step + 1});
            }

        }

        return -1;
    }


}
