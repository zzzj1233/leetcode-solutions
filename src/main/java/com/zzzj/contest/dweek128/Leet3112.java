package com.zzzj.contest.dweek128;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-05-24 17:00
 */
public class Leet3112 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(minimumTime(3, LeetUtils.convertInts("[[0,1,2],[1,2,1],[0,2,4]]"), new int[]{1, 1, 5})));

        System.out.println(Arrays.toString(minimumTime(3, LeetUtils.convertInts("[[0,1,2],[1,2,1],[0,2,4]]"), new int[]{1, 3, 5})));

        System.out.println(Arrays.toString(minimumTime(3, LeetUtils.convertInts("[[2,0,9],[1,0,5],[2,2,4],[0,1,10],[1,1,10],[1,1,10],[2,2,10],[1,1,10]]"), new int[]{4, 13, 19})));
    }

    public static int[] minimumTime(int n, int[][] edges, int[] disappear) {

        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>(n);

        for (int[] edge : edges) {
            Map<Integer, Integer> inner = graph.computeIfAbsent(edge[0], integer -> new HashMap<>());
            if (inner.getOrDefault(edge[1], Integer.MAX_VALUE) > edge[2])
                inner.put(edge[1], edge[2]);
            inner = graph.computeIfAbsent(edge[1], integer -> new HashMap<>());
            if (inner.getOrDefault(edge[0], Integer.MAX_VALUE) > edge[2])
                inner.put(edge[0], edge[2]);
        }

        int[] dis = new int[n];

        PriorityQueue<int[]> queue = new PriorityQueue<>(n, Comparator.comparingInt(o -> o[1]));

        boolean[] vis = new boolean[n];

        queue.add(new int[2]);

        Arrays.fill(dis, Integer.MAX_VALUE);

        while (!queue.isEmpty()) {

            int[] rm = queue.remove();

            int node = rm[0];

            int d = rm[1];

            if (vis[node] || d >= disappear[node])
                continue;

            dis[node] = d;
            vis[node] = true;

            for (Map.Entry<Integer, Integer> adj : graph.getOrDefault(node, Collections.emptyMap()).entrySet()) {

                Integer next = adj.getKey();

                int nextDis = d + adj.getValue();

                if (vis[next] || nextDis > dis[next])
                    continue;

                queue.add(new int[]{next, nextDis});
            }

        }

        for (int i = 0; i < n; i++)
            if (dis[i] == Integer.MAX_VALUE)
                dis[i] = -1;

        return dis;
    }


}
