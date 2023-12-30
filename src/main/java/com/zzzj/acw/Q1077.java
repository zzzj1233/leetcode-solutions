package com.zzzj.acw;

import java.util.*;

public class Q1077 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] w = new int[N + 1];

        Map<Integer, Set<Integer>> tree = new HashMap<>(8);

        boolean[] st = new boolean[N + 1];

        while (scanner.hasNextInt()) {

            int node = scanner.nextInt();

            w[node] = scanner.nextInt();

            int M = scanner.nextInt();

            Set<Integer> children = new HashSet<>(M);

            for (int i = 0; i < M; i++) {
                int child = scanner.nextInt();
                children.add(child);
                st[child] = true;
            }

            tree.put(node, children);
        }

        int root = 1;

        while (st[root]) root++;

        int[][] f = new int[N + 1][3];

        dfs(f, w, root, tree);

        System.out.println(
                Math.min(
                        f[root][1],
                        f[root][2]
                )
        );
    }

    // 0 = 通过父节点照亮
    // 1 = 通过子节点照亮
    // 2 = 自己照亮
    private static void dfs(
            int[][] f,
            int[] w,
            int node,
            Map<Integer, Set<Integer>> tree
    ) {

        f[node][2] = w[node];

        Set<Integer> children = tree.get(node);

        if (children == null) {
            f[node][1] = Integer.MAX_VALUE;
        } else {

            for (Integer child : children) {

                dfs(f, w, child, tree);

                f[node][0] += Math.min(
                        f[child][1],
                        f[child][2]
                );

                f[node][2] += Math.min(
                        f[child][0],
                        Math.min(
                                f[child][1],
                                f[child][2]
                        )
                );

            }

            int sum = f[node][0];

            f[node][1] = Integer.MAX_VALUE;

            for (Integer child : children) {
                f[node][1] = Math.min(
                        f[node][1],
                        sum - Math.min(
                                f[child][1],
                                f[child][2]
                        ) + f[child][2]
                );
            }

        }


    }

}
