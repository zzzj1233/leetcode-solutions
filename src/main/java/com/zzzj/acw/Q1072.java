package com.zzzj.acw;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q1072 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        Map<Integer, Map<Integer, Integer>> tree = new HashMap<>(N);

        while (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            tree.computeIfAbsent(a, ignored -> new HashMap<>(4)).put(b, c);
            tree.computeIfAbsent(b, ignored -> new HashMap<>(4)).put(a, c);
        }

        int[] f = new int[N + 1];

        dfs(f, 1, tree, -1);

        System.out.println(Arrays.stream(f).max().orElse(0));
    }

    private static int dfs(int[] f, int node, Map<Integer, Map<Integer, Integer>> tree, int parent) {

        Map<Integer, Integer> children = tree.get(node);

        if (children == null)
            return 0;

        int max = 0;
        int sec = 0;

        for (Map.Entry<Integer, Integer> child : children.entrySet()) {

            if (child.getKey() == parent)
                continue;

            int val = dfs(f, child.getKey(), tree, node) + child.getValue();

            if (val >= max) {
                sec = max;
                max = val;
            } else if (val > sec)
                sec = val;
        }

        f[node] = sec + max;

        return max;
    }


}
