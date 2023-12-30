package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.*;

public class Leet834 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(sumOfDistancesInTree(6, LeetUtils.convertInts("[[0,1],[0,2],[2,3],[2,4],[2,5]]"))));

    }

    public static int[] sumOfDistancesInTree(int n, int[][] edges) {

        Map<Integer, Set<Integer>> tree = new HashMap<>(n);

        for (int[] edge : edges) {
            tree.computeIfAbsent(edge[0], integer -> new HashSet<>()).add(edge[1]);
            tree.computeIfAbsent(edge[1], integer -> new HashSet<>()).add(edge[0]);
        }

        int[] ans = new int[n];

        int[] cnt = new int[n];

        ans[0] = dfs(cnt, 0, -1, 0, tree);

        reRoot(ans, cnt, 0, -1, tree);

        return ans;
    }

    private static void reRoot(
            int[] ans,
            int[] cnt,
            int node,
            int parent,
            Map<Integer, Set<Integer>> tree
    ) {
        if (parent != -1)
            ans[node] = ans[parent] + cnt[0] - (cnt[node] << 1);
        for (Integer child : tree.get(node)) {
            if (child != parent)
                reRoot(ans, cnt, child, node, tree);
        }
    }

    private static int dfs(
            int[] cnt,
            int node,
            int parent,
            int depth,
            Map<Integer, Set<Integer>> tree
    ) {
        int res = depth;

        cnt[node] = 1;

        if (!tree.containsKey(node))
            return 0;

        for (Integer child : tree.get(node)) {

            if (child == parent)
                continue;

            res += dfs(cnt, child, node, depth + 1, tree);

            cnt[node] += cnt[child];
        }

        return res;
    }


}
