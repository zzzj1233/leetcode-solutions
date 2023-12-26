package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-12-14 15:09
 */
public class Leet1000 {

    public static int mergeStones(int[] stones, int K) {

        int N = stones.length;

        int[][][] f = new int[N][N][K + 1];

        int[] p = new int[N + 1];

        for (int i = 1; i <= N; i++)
            f[i][i][1] = stones[i];

        for (int len = 1; len < N; len++) {

            for (int left = 0; left + len < N; left++) {

                int right = left + len;

                int end = Math.min(len + 1, K);

                f[left][right][1] = p[right + 1] - p[left];

                for (int k = 2; k <= end; k++) {

                }

            }

        }


        return -1;
    }

}
