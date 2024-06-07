package com.zzzj.contest.week394;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-05-24 18:54
 */
public class Leet3123 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(findAnswer(6, LeetUtils.convertInts("[[0,1,4],[0,2,1],[1,3,2],[1,4,3],[1,5,1],[2,3,1],[3,5,3],[4,5,2]]"))));

    }

    public static boolean[] findAnswer(int n, int[][] edges) {

        Map<Integer, Map<Integer, int[]>> graph = new HashMap<>();

        int N = edges.length;

        for (int i = 0; i < N; i++) {
            int[] v = {edges[i][2], i};

            graph.computeIfAbsent(edges[i][0], integer -> new HashMap<>())
                    .put(edges[i][1], v);

            graph.computeIfAbsent(edges[i][1], integer -> new HashMap<>())
                    .put(edges[i][0], v);
        }

        int[] dis = new int[n];

        boolean[] vis = new boolean[n];

        PriorityQueue<int[]> queue = new PriorityQueue<>(n, Comparator.comparingInt(o -> o[1]));

        queue.add(new int[2]);

        Arrays.fill(dis, Integer.MAX_VALUE);

        while (!queue.isEmpty()) {

            int[] rm = queue.remove();

            int node = rm[0];

            int dist = rm[1];

            if (vis[node])
                continue;

            dis[node] = dist;

            vis[node] = true;

            for (Map.Entry<Integer, int[]> entry : graph.getOrDefault(node, Collections.emptyMap()).entrySet()) {

                Integer next = entry.getKey();

                int v = entry.getValue()[0];

                if (vis[next] || dis[next] < dist + v)
                    continue;

                queue.add(new int[]{next, dist + v});
            }

        }

        boolean[] ans = new boolean[N];

        if (dis[n - 1] != Integer.MAX_VALUE)
            dfs(graph, 0, n - 1, -1, 0, dis[n - 1], ans);

        return ans;
    }

    public static boolean dfs(
            Map<Integer, Map<Integer, int[]>> graph,
            int cur,
            int target,
            int prev,
            int dis,
            int ed,
            boolean[] ans
    ) {

        if (dis > ed)
            return false;

        if (target == cur)
            return dis == ed;

        boolean res = false;

        for (Map.Entry<Integer, int[]> entry : graph.getOrDefault(cur, Collections.emptyMap()).entrySet()) {

            Integer next = entry.getKey();

            if (next == prev)
                continue;

            if (dfs(graph, next, target, cur, dis + entry.getValue()[0], ed, ans)) {
                ans[entry.getValue()[1]] = true;
                res = true;
            }

        }

        return res;
    }

}
