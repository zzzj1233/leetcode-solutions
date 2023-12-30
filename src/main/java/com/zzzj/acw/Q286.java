package com.zzzj.acw;

import java.util.*;

public class Q286 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner("7 4\n" +
                "2 2\n" +
                "0 1\n" +
                "0 4\n" +
                "2 1\n" +
                "7 1\n" +
                "7 6\n" +
                "2 2");

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        int N = scanner.nextInt();

        int M = scanner.nextInt();

        int[] w = new int[N + 1];

        for (int i = 1; i <= N; i++) {

            int prev = scanner.nextInt();

            graph.computeIfAbsent(prev, integer -> new HashSet<>()).add(i);

            // 分数
            w[i] = scanner.nextInt();
        }

        int[][] dp = new int[N + 1][M + 2];

        dfs(0, graph, dp, w, M + 1);

        System.out.println(dp[0][M + 1]);
    }

    private static void dfs(
            int node,
            Map<Integer, Set<Integer>> graph,
            int[][] dp,
            int[] w,
            int M
    ) {

        Set<Integer> successors = graph.getOrDefault(node, Collections.emptySet());

        for (Integer successor : successors) {

            dfs(
                    successor,
                    graph,
                    dp,
                    w,
                    M
            );

            for (int i = M; i >= 0; i--) {
                for (int k = 0; k <= i; k++) {
                    dp[node][i] = Math.max(
                            dp[node][i],
                            dp[successor][k] + dp[node][i - k]
                    );
                }
            }

        }

        for (int i = M; i > 1; i--)
            dp[node][i] = dp[node][i - 1] + w[node];

        dp[node][1] = w[node];
    }

}
