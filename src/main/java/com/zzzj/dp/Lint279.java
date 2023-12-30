package com.zzzj.dp;

public class Lint279 {

    public static int waysNCents(int n) {

        int[] W = {25, 10, 5, 1};

        int[] f = new int[n + 1];

        f[0] = 1;

        for (int w : W) {
            for (int v = w; v <= n; v++) {
                f[v] += f[v - w];
            }
        }

        return f[n];
    }

}
