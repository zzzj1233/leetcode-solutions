package com.zzzj.acw;

import java.util.*;

public class Q10 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int M = scanner.nextInt();

        int root = -1;

        int[][] w = new int[N][2];

        Map<Integer, Set<Integer>> children = new HashMap<>();

        for (int i = 0; i < N; i++) {
            w[i][0] = scanner.nextInt();
            w[i][1] = scanner.nextInt();
            int p = scanner.nextInt();
            if (p == -1)
                root = i;
            else
                children.computeIfAbsent(p - 1, integer -> new HashSet<>())
                        .add(i);
        }

        int[][] dp = new int[N][M + 1];

        dfs(
                root,
                dp,
                w,
                M,
                children
        );

        System.out.println(dp[root][M]);
    }

    private static void dfs(
            int node,
            int[][] dp,
            int[][] w,
            int M,
            Map<Integer, Set<Integer>> children
    ) {

        if (w[node][0] > M)
            return;

        Set<Integer> c = children.getOrDefault(node, Collections.emptySet());

        for (Integer child : c)
            dfs(child, dp, w, M, children);

        // dp[node][w[node][0]] = w[node][1];

        for (Integer child : c) {

            for (int k = M; k >= 0; k--) {

                for (int x = 0; x <= k; x++) {

                    dp[node][k] = Math.max(
                            dp[node][k],
                            dp[child][x] + dp[node][k - x]
                    );
                }

            }

        }

        for (int i = M; i >= w[node][0]; i--) {
            dp[node][i] = dp[node][i - w[node][0]] + w[node][1];
        }

        for (int i = 0; i < w[node][0]; i++) {
            dp[node][i] = 0;
        }

    }

}
