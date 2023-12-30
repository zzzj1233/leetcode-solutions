package com.zzzj.dp;

public class Lint92 {

    public static void main(String[] args) {

        System.out.println(backPack(10, new int[]{3, 4, 8, 5}));

    }

    public static int backPack(int m, int[] a) {

        int[] f = new int[m + 1];

        int N = a.length;

        for (int i = 0; i < N; i++) {

            int w = a[i];

            for (int v = m; v >= w; v--)
                f[v] = Math.max(f[v], f[v - w] + w);

        }

        return f[m];
    }

}
