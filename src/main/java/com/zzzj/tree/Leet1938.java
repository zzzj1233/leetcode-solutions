package com.zzzj.tree;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-01-19 14:21
 */
public class Leet1938 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(maxGeneticDifference(new int[]{-1, 0, 0, 0, 3}, LeetUtils.convertInts("[[4,6],[0,0],[0,3],[1,8],[4,0]]"))));

    }

    public static int[] maxGeneticDifference(int[] parents, int[][] queries) {

        int M = queries.length;

        Map<Integer, List<int[]>> q = new HashMap<>(M);

        for (int i = 0; i < M; i++)
            q.computeIfAbsent(queries[i][0], integer -> new ArrayList<>()).add(new int[]{i, queries[i][1]});

        int root = -1;

        Map<Integer, Set<Integer>> tree = new HashMap<>();

        for (int i = 0; i < parents.length; i++)
            if (parents[i] == -1)
                root = i;
            else
                tree.computeIfAbsent(parents[i], integer -> new HashSet<>()).add(i);

        int[] ans = new int[M];

        dfs(new Trie(), root, tree, ans, q);

        return ans;
    }

    private static void dfs(
            Trie trie,
            int node,
            Map<Integer, Set<Integer>> tree,
            int[] ans,
            Map<Integer, List<int[]>> q
    ) {
        append(trie, node);

        List<int[]> query = q.get(node);

        if (query != null)
            for (int[] qi : query)
                ans[qi[0]] = searchMax(trie, qi[1]);

        for (Integer child : tree.getOrDefault(node, Collections.emptySet()))
            dfs(trie, child, tree, ans, q);

        erase(trie, node);
    }

    private static int searchMax(Trie trie, int val) {

        int res = 0;

        for (int i = 31; i >= 0; i--) {

            if ((val & (1 << i)) != 0) {
                if (trie.zero == null) {
                    trie = trie.one;
                } else {
                    res |= 1 << i;
                    trie = trie.zero;
                }
            } else {
                if (trie.one == null) {
                    trie = trie.zero;
                } else {
                    res |= 1 << i;
                    trie = trie.one;
                }
            }

        }

        return res;
    }

    private static void append(Trie trie, int val) {

        Trie root = trie;

        for (int i = 31; i >= 0; i--) {

            if ((val & (1 << i)) != 0) {
                if (root.one == null)
                    root.one = new Trie();
                root = root.one;
            } else {
                if (root.zero == null)
                    root.zero = new Trie();
                root = root.zero;
            }

            root.cnt++;
        }

    }

    private static void erase(Trie trie, int val) {

        Trie root = trie;

        for (int i = 31; i >= 0; i--) {

            Trie next;

            boolean one = (val & (1 << i)) != 0;

            if (one) {
                next = root.one;
            } else {
                next = root.zero;
            }

            next.cnt--;

            if (next.cnt == 0)
                if (one)
                    root.one = null;
                else
                    root.zero = null;

            root = next;
        }
    }

    private static class Trie {
        Trie one;
        Trie zero;
        int cnt;
    }

}
