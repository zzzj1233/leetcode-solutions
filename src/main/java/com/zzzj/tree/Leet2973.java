package com.zzzj.tree;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-12-27 16:29
 */
public class Leet2973 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(placedCoins(LeetUtils.convertInts("[[0,1],[0,2],[0,3],[0,4],[0,5]]"), new int[]{1, 2, 3, 4, 5, 6})));

    }

    public static long[] placedCoins(int[][] edges, int[] cost) {

        Map<Integer, Set<Integer>> tree = new HashMap<>();

        for (int[] edge : edges) {
            tree.computeIfAbsent(edge[0], integer -> new HashSet<>()).add(edge[1]);
            tree.computeIfAbsent(edge[1], integer -> new HashSet<>()).add(edge[0]);
        }

        long[] ans = new long[cost.length];

        dfs(
                tree,
                0,
                -1,
                cost,
                ans
        );

        return ans;
    }

    public static long max(ArrayList<Integer> values) {
        int N = values.size();
        if (N < 3)
            return 1;
        return Math.max(
                Math.max(
                        (long) values.get(0) * values.get(1) * values.get(N - 1),
                        (long) values.get(N - 3) * values.get(N - 2) * values.get(N - 1)
                ),
                0L
        );
    }

    public static ArrayList<Integer> dfs(
            Map<Integer, Set<Integer>> tree,
            int node,
            int parent,
            int[] cost,
            long[] ans
    ) {

        ArrayList<Integer> values = new ArrayList<>();

        values.add(cost[node]);

        for (Integer child : tree.get(node)) {

            if (child == parent)
                continue;

            ArrayList<Integer> v = dfs(tree, child, node, cost, ans);

            values.addAll(v);
        }

        Collections.sort(values);

        ans[node] = max(values);

        int N = values.size();

        if (N < 5) {
            return values;
        }

        ArrayList<Integer> res = new ArrayList<>(5);

        res.add(values.get(0));
        res.add(values.get(1));
        res.add(values.get(N - 3));
        res.add(values.get(N - 2));
        res.add(values.get(N - 1));

        return res;
    }

}
