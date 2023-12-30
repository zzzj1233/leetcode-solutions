package com.zzzj.acw;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q1074 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int M = scanner.nextInt();

        Map<Integer, Map<Integer, Integer>> tree = new HashMap<>(N);

        while (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            tree.computeIfAbsent(a, ignored -> new HashMap<>(4)).put(b, c);
            tree.computeIfAbsent(b, ignored -> new HashMap<>(4)).put(a, c);
        }

        int[][] f = new int[N + 1][M + 1];

        dfs(
                1,
                -1,
                M,
                tree,
                f
        );

        System.out.println(f[1][M]);
    }

    private static void dfs(
            int node,
            int parent,
            int M,
            Map<Integer, Map<Integer, Integer>> tree,
            int[][] f
    ) {

        Map<Integer, Integer> adj = tree.get(node);

        for (Map.Entry<Integer, Integer> child : adj.entrySet()) {

            if (child.getKey() == parent)
                continue;

            dfs(
                    child.getKey(),
                    node,
                    M,
                    tree,
                    f
            );

            for (int v = M; v > 0; v--) {

                for (int k = 0; k < v; k++) {

                    f[node][v] = Math.max(
                            f[node][v],
                            f[node][v - k - 1] + f[child.getKey()][k] + child.getValue()
                    );

                }

            }

        }


    }

}
