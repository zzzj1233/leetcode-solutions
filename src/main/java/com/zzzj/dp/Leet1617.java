package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-01-10 11:23
 */
public class Leet1617 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(countSubgraphsForEachDiameter(4, LeetUtils.convertInts("[[1,2],[2,3],[2,4]]"))));

    }

    public static int[] countSubgraphsForEachDiameter(int n, int[][] edges) {

        Map<Integer, Set<Integer>> graph = new HashMap<>(n);

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0] - 1, integer -> new HashSet<>()).add(edge[1] - 1);
            graph.computeIfAbsent(edge[1] - 1, integer -> new HashSet<>()).add(edge[0] - 1);
        }

        int limit = 1 << n;

        int[] ans = new int[n - 1];

        for (int stat = 1; stat < limit; stat++) {

            if (Integer.bitCount(stat) == 1)
                continue;

            maxDis = -1;

            int max = -1;

            for (int i = 0; i <= 31; i++) {
                if ((stat & (1 << i)) != 0) {
                    mask = stat;
                    dfs(i, -1, graph);
                    if (mask == 0)
                        max = Math.max(max, maxDis);
                }
            }

            if (max != -1)
                ans[max - 1]++;

        }

        return ans;
    }

    static int maxDis;

    static int mask;

    public static int dfs(int node, int fa, Map<Integer, Set<Integer>> graph) {

        int max = 0;
        int sec = 0;

        mask ^= 1 << node;

        for (Integer adj : graph.get(node)) {

            if (adj == fa || (mask & (1 << adj)) == 0)
                continue;

            int v = dfs(adj, node, graph) + 1;

            if (v >= max) {
                sec = max;
                max = v;
            } else if (v > sec) {
                sec = v;
            }

        }

        maxDis = Math.max(max, max + sec);

        return max;
    }


}
