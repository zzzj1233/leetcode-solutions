package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-01-30 17:43
 */
public class Leet2318 {

    public static void main(String[] args) {

        System.out.println(distinctSequences(4));

    }

    public static int distinctSequences(int n) {

        int MOD = 1000000007;

        int V = 7;

        boolean[][] gcd = new boolean[V][V];

        for (int i = 1; i < V; i++)
            for (int j = 1; j < V; j++)
                gcd[i][j] = gcd(i, j) == 1;

        // num
        // prev
        // prevPrev
        int[][][] f = new int[V][V][V];

        for (int i = 1; i < V; i++)
            for (int j = 1; j < V; j++)
                if (gcd[i][j])
                    Arrays.fill(f[i][j], 1);

        for (int i = 2; i <= n; i++) {

            int[][][] next = new int[V][V][V];

            for (int num = 1; num < V; num++) {

                for (int prev = 1; prev < V; prev++) {

                    if (!gcd[num][prev] || prev == num)
                        continue;

                    int[][] ff = f[prev];

                    for (int prevPrev = 0; prevPrev < V; prevPrev++) {

                        if (!gcd[prev][prevPrev] || prevPrev == num)
                            continue;

                        int[] fff = ff[prevPrev];

                        for (int ppp = 1; ppp < V; ppp++) {
                            if (ppp != num)
                                next[num][prev][prevPrev] += fff[ppp];
                        }

                    }

                }

            }

            f = next;
        }

        int ans = 0;

        for (int i = 1; i < V; i++) {
            for (int j = 1; j < V; j++) {
                for (int k = 1; k < V; k++) {
                    ans += f[i][j][k];
                }
            }
        }

        return ans;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
