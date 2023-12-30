package com.zzzj.dp;

public class Lint273 {

    public static int exam(int[] p, int[] part, int[] f, int[] full) {

        int V = 121;

        int[] dp = new int[V];

        int N = p.length;

        for (int i = 0; i < N; i++) {

            for (int v = V - 1; v >= 0; v--) {
                if (v >= p[i])
                    dp[v] = Math.max(dp[v], dp[v - p[i]] + part[i]);
                if (v >= f[i])
                    dp[v] = Math.max(dp[v], dp[v - f[i]] + full[i]);
            }

        }

        return dp[120];
    }

}
