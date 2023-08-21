package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-08-21 12:18
 */
public class Leet2646 {

    public static void main(String[] args) {

        System.out.println(minimumTotalPrice(
                4,
                LeetUtils.convertInts("[[0,1],[1,2],[1,3]]"),
                LeetUtils.convertInts1("[2,2,10,6]"),
                LeetUtils.convertInts("[[0,3],[2,1],[2,3]]")
        ));

        System.out.println(minimumTotalPrice(
                9,
                LeetUtils.convertInts("[[2,5],[3,4],[4,1],[1,7],[6,7],[7,0],[0,5],[5,8]]"),
                LeetUtils.convertInts1("[4,4,6,4,2,4,2,14,8]"),
                LeetUtils.convertInts("[[1,5],[2,7],[4,3],[1,8],[2,8],[4,3],[1,5],[1,4],[2,1],[6,0],[0,7],[8,6],[4,0],[7,5],[7,5],[6,0],[5,1],[1,1],[7,5],[1,7],[8,7],[2,3],[4,1],[3,5],[2,5],[3,7],[0,1],[5,8],[5,3],[5,2]]")
        ));

    }

    public static int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], ignore -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], ignore -> new HashSet<>()).add(edge[0]);
        }

        int[] pathVisitCnt = new int[n];

        for (int[] trip : trips) {
            int start = trip[0];
            int end = trip[1];

            // 树: 没有环, 所以从start - end的路径一定是单一的, 使用dfs累计访问路径
            visit(graph, pathVisitCnt, start, start, end);
        }

        int[] dfs = dfs(graph, pathVisitCnt, price, 0, 0);

        return Math.min(
                dfs[0],
                dfs[1]
        );
    }

    public static int[] dfs(
            Map<Integer, Set<Integer>> graph,
            int[] pathVisitCnt,
            int[] price,
            int current,
            int prev
    ) {
        int[] res = new int[2];

        int[] a = new int[2];

        for (Integer adj : graph.get(current)) {

            if (adj == prev) continue;

            int[] dfs = dfs(graph, pathVisitCnt, price, adj, current);
            a[0] += dfs[0];
            a[1] += Math.min(dfs[1], dfs[0]);
        }

        res[0] = pathVisitCnt[current] * price[current] + Math.min(a[0], a[1]);
        res[1] = pathVisitCnt[current] * (price[current] / 2) + a[0];

        return res;
    }

    public static boolean visit(Map<Integer, Set<Integer>> graph, int[] pathVisitCnt, int current, int prev, int end) {
        if (current == end) {
            pathVisitCnt[end]++;
            return true;
        }

        for (Integer adj : graph.getOrDefault(current, Collections.emptySet())) {
            if (adj != prev && visit(graph, pathVisitCnt, adj, current, end)) {
                pathVisitCnt[current]++;
                return true;
            }
        }

        return false;
    }

}
