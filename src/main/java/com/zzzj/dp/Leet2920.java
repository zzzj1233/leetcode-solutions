package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.*;

public class Leet2920 {

    public static void main(String[] args) {

//        System.out.println(maximumPoints(
//                LeetUtils.convertInts("[[0,1],[1,2],[2,3]]"),
//                new int[]{10, 10, 3, 3},
//                5
//        ));
//
//        System.out.println(maximumPoints(
//                LeetUtils.convertInts("[[0,1],[0,2]]"),
//                new int[]{8, 4, 4},
//                0
//        ));

        System.out.println(maximumPoints(
                LeetUtils.convertInts("[[0,1],[2,1]]"),
                new int[]{1, 6, 4},
                4
        ));

    }

    public static int maximumPoints(int[][] edges, int[] coins, int k) {

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], integer -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], integer -> new HashSet<>()).add(edge[0]);
        }

        int[][] memo = new int[coins.length][15];

        for (int i = 0; i < coins.length; i++)
            Arrays.fill(memo[i], -1);

        return dp(0, 0, memo, coins, k, graph, -1);
    }

    private static int dp(
            int current,
            int times,
            int[][] memo,
            int[] coins,
            int k,
            Map<Integer, Set<Integer>> graph,
            int prev
    ) {

        if (memo[current][times] != -1)
            return memo[current][times];

        int case1 = (coins[current] >> times) - k;

        Set<Integer> children = graph.getOrDefault(current, Collections.emptySet());

        for (Integer c : children)
            if (c != prev)
                case1 += dp(c, times, memo, coins, k, graph, current);

        int case2 = coins[current] >> (times + 1);

        for (Integer c : children)
            if (c != prev && times + 1 <= 14)
                case2 += dp(c, times + 1, memo, coins, k, graph, current);

        int res = Math.max(case1, case2);

        memo[current][times] = res;

        return res;
    }


}
