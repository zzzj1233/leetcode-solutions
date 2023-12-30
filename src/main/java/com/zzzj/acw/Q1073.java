package com.zzzj.acw;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q1073 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner("5 \n" +
                "2 1 1 \n" +
                "3 2 1 \n" +
                "4 3 1 \n" +
                "5 1 1");

        int N = scanner.nextInt();

        Map<Integer, Map<Integer, Integer>> tree = new HashMap<>(N);

        while (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            tree.computeIfAbsent(a, ignored -> new HashMap<>(4)).put(b, c);
            tree.computeIfAbsent(b, ignored -> new HashMap<>(4)).put(a, c);
        }
        int[] up = new int[N + 1];
        int[] d1 = new int[N + 1];
        int[] p = new int[N + 1];
        int[] d2 = new int[N + 1];

        down(d1, p, d2, 1, tree, -1);

        up(up, d1, p, d2, 1, tree, -1);

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            ans = Math.min(ans, Math.max(
                    up[i], d1[i]
            ));
        }

        System.out.println(ans);
    }

    private static void up(
            int[] up,
            int[] d1,
            int[] p,
            int[] d2,
            int node,
            Map<Integer, Map<Integer, Integer>> tree,
            int parent
    ) {

        Map<Integer, Integer> adj = tree.get(node);

        if (parent != -1) {
            up[node] = Math.max(up[parent], (p[parent] == node ? d2[parent] : d1[parent])) + adj.get(parent);
        }

        for (Map.Entry<Integer, Integer> child : adj.entrySet()) {
            if (child.getKey() == parent)
                continue;
            up(up, d1, p, d2, child.getKey(), tree, node);
        }

    }

    private static int down(
            int[] d1,
            int[] p,
            int[] d2,
            int node,
            Map<Integer, Map<Integer, Integer>> tree,
            int parent
    ) {

        for (Map.Entry<Integer, Integer> child : tree.get(node).entrySet()) {

            if (child.getKey() == parent)
                continue;

            int val = down(
                    d1,
                    p,
                    d2,
                    child.getKey(),
                    tree,
                    node
            ) + child.getValue();

            if (val >= d1[node]) {
                d2[node] = d1[node];
                d1[node] = val;
                p[node] = child.getKey();
            } else if (val > d2[node]) {
                d2[node] = val;
            }

        }

        return d1[node];
    }

}
