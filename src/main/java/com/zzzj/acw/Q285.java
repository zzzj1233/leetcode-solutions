package com.zzzj.acw;

import java.util.*;

public class Q285 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] w = new int[N + 1];

        for (int i = 1; i <= N; i++)
            w[i] = scanner.nextInt();

        Map<Integer, Set<Integer>> tree = new HashMap<>(N);

        boolean[] children = new boolean[N + 1];

        while (scanner.hasNextInt()) {
            int child = scanner.nextInt();
            int parent = scanner.nextInt();
            tree.computeIfAbsent(parent, integer -> new HashSet<>()).add(child);
            children[child] = true;
        }

        int root = -1;

        for (int i = 1; i <= N; i++) {
            if (!children[i]) {
                root = i;
                break;
            }
        }

        int[][] f = new int[N + 1][2];

        dfs(f, w, root, tree);

        System.out.println(Math.max(f[root][0], f[root][1]));
    }

    private static void dfs(
            int[][] f,
            int[] w,
            int node,
            Map<Integer, Set<Integer>> tree
    ) {

        Set<Integer> children = tree.get(node);

        f[node][0] = w[node];

        if (children == null) {
            return;
        }

        for (Integer child : children) {
            dfs(f, w, child, tree);
            f[node][0] += f[child][1];
            f[node][1] += Math.max(f[child][0], f[child][1]);
        }

    }

}
