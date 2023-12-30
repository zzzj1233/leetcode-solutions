package com.zzzj.dp;

public class Lint1853 {

    public static int maxWeight(int n, int[] weights, int[] tasks, int p) {

        int[] f = new int[p + 1];

        for (int i = 0; i < n; i++) {

            int w = tasks[i] << 1;

            for (int v = p; v >= w; v--) {
                f[v] = Math.max(
                        f[v],
                        f[v - w] + weights[i]
                );
            }

        }

        return f[p];
    }

}
