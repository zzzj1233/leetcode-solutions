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

//        System.out.println(shortestPathLength(LeetUtils.convertInts("[[1],[0]]")));

//        System.out.println(shortestPathLength(LeetUtils.convertInts("[[1,2,3],[0],[0],[0]]")));

//        System.out.println(shortestPathLength(LeetUtils.convertInts("[[1],[0,2,4],[1,3,4],[2],[1,2]]")));

//        System.out.println(shortestPathLength(LeetUtils.convertInts("[[1],[0,2,4],[1,3],[2],[1,5],[4]]")));

        System.out.println(shortestPathLength(LeetUtils.convertInts("[[2,3],[7],[0,6],[0,4,7],[3,8],[7],[2],[5,3,1],[4]]")));

    }

    public static int shortestPathLength(int[][] graph) {

        int N = graph.length;

        if (Arrays.stream(graph).allMatch(arr -> arr.length == 0)) {
            return 0;
        }

        int[] g = new int[N];

        for (int i = 0; i < N; i++)
            for (int adj : graph[i]) {
                g[i] |= 1 << adj;
                g[adj] |= 1 << i;
            }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++)
            ans = Math.min(ans, bfs(N, i, g));

        return ans;
    }

    public static int bfs(int N, int start, int[] g) {

        boolean[][] visited = new boolean[N][1 << N];

        LinkedList<int[]> queue = new LinkedList<>();

        queue.add(new int[]{start, 0, 1 << start});

        int ans = Integer.MAX_VALUE;

        int expect = (1 << N) - 1;

        while (!queue.isEmpty()) {

            int[] rm = queue.removeFirst();

            int node = rm[0];

            int step = rm[1];

            int mask = rm[2];

            if (mask == expect) {
                ans = Math.min(ans, step);
                continue;
            }

            for (int i = 0; i < N; i++) {

                if ((g[node] & (1 << i)) != 0 && !visited[i][mask | (1 << i)]) {

                    visited[i][mask | (1 << i)] = true;

                    queue.addLast(new int[]{i, step + 1, mask | (1 << i)});

                }

            }

        }

        return ans;
    }


}
