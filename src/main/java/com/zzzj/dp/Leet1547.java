package com.zzzj.dp;

import java.util.Arrays;

public class Leet1547 {

    public static void main(String[] args) {

        System.out.println(minCost(7, new int[]{1, 3, 4, 5}));

        System.out.println(minCost(9, new int[]{5, 6, 1, 4, 2}));

    }

    public static int minCost(int n, int[] cuts) {

        int N = cuts.length;

        Arrays.sort(cuts);

        int[] w = new int[N + 2];

        for (int i = 1; i <= N; i++)
            w[i] = cuts[i - 1];

        w[N + 1] = n;

        N = w.length;

        int[][] f = new int[N][N];

        for (int len = 2; len < N; len++) {

            for (int left = 0; left + len < N; left++) {

                int right = left + len;

                f[left][right] = Integer.MAX_VALUE / 2;

                for (int k = left + 1; k < right; k++) {
                    f[left][right] = Math.min(
                            f[left][right],
                            f[left][k] + f[k][right] + (w[right] - w[left])
                    );
                }

            }

        }

        return f[0][N - 1];
    }


}
