package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Leet310 {

    public static void main(String[] args) {

        System.out.println(findMinHeightTrees(6, LeetUtils.convertInts("[[0,1],[0,2],[0,3],[3,4],[4,5]]")));

        // System.out.println(findMinHeightTrees(4, LeetUtils.convertInts("[[1,0],[1,2],[1,3]]")));

        // System.out.println(findMinHeightTrees(6, LeetUtils.convertInts("[[3,0],[3,1],[3,2],[3,4],[5,4]]")));

    }


    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {

        Map<Integer, Set<Integer>> tree = new HashMap<>(n);

        for (int[] edge : edges) {
            tree.computeIfAbsent(edge[0], integer -> new HashSet<>()).add(edge[1]);
            tree.computeIfAbsent(edge[1], integer -> new HashSet<>()).add(edge[0]);
        }

        int[] max = new int[n];
        int[] sec = new int[n];
        int[] p = new int[n];

        dfs(max, sec, p, 0, -1, tree);

        reRoot(max, sec, p, 0, -1, tree);

        int min = Arrays.stream(max).min().orElse(-1);

        return IntStream.range(0, n)
                .filter(index -> max[index] == min)
                .boxed()
                .collect(Collectors.toList());
    }

    private static void reRoot(
            int[] max,
            int[] sec,
            int[] p,
            int node,
            int parent,
            Map<Integer, Set<Integer>> tree
    ) {
        Set<Integer> children = tree.get(node);

        if (children == null)
            return;

        if (parent != -1) {

            int d;

            if (p[parent] == node) {
                if (sec[parent] + 1 > max[node]) {
                    max[node] = sec[parent] + 1;
                    p[node] = parent;
                    sec[node] = max[node];
                } else {
                    sec[node] = Math.max(
                            sec[node],
                            sec[parent] + 1
                    );
                }
            } else {
                if (max[parent] + 1 > max[node]) {
                    max[node] = max[parent] + 1;
                    p[node] = parent;
                    sec[node] = max[node];
                } else {
                    sec[node] = Math.max(
                            sec[node],
                            max[parent] + 1
                    );
                }
            }

        }

        for (Integer child : children) {

            if (child == parent) {
                continue;
            }

            reRoot(max, sec, p, child, node, tree);
        }

    }

    private static int dfs(
            int[] max,
            int[] sec,
            int[] p,
            int node,
            int parent,
            Map<Integer, Set<Integer>> tree
    ) {

        Set<Integer> children = tree.get(node);

        if (children == null)
            return 0;

        for (Integer child : children) {

            if (child == parent) {
                continue;
            }

            int v = dfs(max, sec, p, child, node, tree) + 1;

            if (v >= max[node]) {
                sec[node] = max[node];
                max[node] = v;
                p[node] = child;
            } else if (v > sec[node]) {
                sec[node] = v;
            }

        }

        return max[node];
    }

}
