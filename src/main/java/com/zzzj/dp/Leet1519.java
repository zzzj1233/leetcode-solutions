package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-12-25 16:15
 */
public class Leet1519 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(countSubTrees(7, LeetUtils.convertInts("[[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]"), "abaedcd")));

    }

    public static int[] countSubTrees(int n, int[][] edges, String labels) {

        Map<Integer, Set<Integer>> tree = new HashMap<>(n);

        for (int[] edge : edges) {
            tree.computeIfAbsent(edge[0], integer -> new HashSet<>()).add(edge[1]);
            tree.computeIfAbsent(edge[1], integer -> new HashSet<>()).add(edge[0]);
        }

        int[] ans = new int[n];

        dfs(0, -1, tree, labels, ans);

        return ans;
    }

    final static int[] EMPTY = new int[26];

    private static int[] dfs(
            int node,
            int parent,
            Map<Integer, Set<Integer>> tree,
            String labels,
            int[] ans
    ) {

        Set<Integer> children = tree.get(node);

        int[] res = new int[26];

        res[labels.charAt(node) - 'a'] = 1;

        if (children == null){
            ans[node] = 1;
            return res;
        }

        for (Integer child : children) {

            if (child == parent)
                continue;

            int[] item = dfs(child, node, tree, labels, ans);

            for (int i = 0; i < 26; i++)
                res[i] += item[i];

        }

        ans[node] = res[labels.charAt(node) - 'a'];

        return res;
    }

}
