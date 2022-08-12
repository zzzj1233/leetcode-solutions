package com.zzzj.tree;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-08-11 12:27
 */
public class Leet1273 {

    public static int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        if (nodes == 0) {
            return 0;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 1; i < parent.length; i++) {
            map.computeIfAbsent(parent[i], ignore -> new ArrayList<>())
                    .add(i);
        }

        return dfs(value, 0, map)[1];
    }

    public static int[] EMPTY = new int[2];

    public static int[] dfs(int[] value, int cur, Map<Integer, List<Integer>> map) {
        if (cur >= value.length) {
            return EMPTY;
        }

        List<Integer> children = map.getOrDefault(cur, Collections.emptyList());

        int totalValue = value[cur];

        int totalCount = 1;

        for (Integer child : children) {
            int[] dfs = dfs(value, child, map);
            totalValue += dfs[0];
            totalCount += dfs[1];
        }

        if (totalValue == 0) {
            return EMPTY;
        }

        return new int[]{totalValue, totalCount};
    }


}
