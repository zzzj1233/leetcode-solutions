package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-01-29 15:08
 */
public class Leet514 {

    public static void main(String[] args) {

        System.out.println(findRotateSteps("abcde", "ade"));

        System.out.println(findRotateSteps("godding", "gd"));

    }

    public static int findRotateSteps(String ring, String key) {

        int N = ring.length();

        int M = key.length();

        int[][] f = new int[M][N];

        for (int i = 0; i < M; i++)
            Arrays.fill(f[i], Integer.MAX_VALUE);

        for (int i = 0; i < N; i++)
            if (ring.charAt(i) == key.charAt(0))
                f[0][i] = Math.min(i, N - i) + 1;

        for (int i = 1; i < M; i++) {

            for (int j = 0; j < N; j++) {

                if (f[i - 1][j] == Integer.MAX_VALUE)
                    continue;

                for (int k = 0; k < N; k++) {

                    if (ring.charAt(k) == key.charAt(i)) {

                        f[i][k] = Math.min(
                                f[i][k],
                                f[i - 1][j] + Math.min(
                                        Math.abs(j - k),
                                        N - Math.max(j, k) + Math.min(j, k)
                                ) + 1
                        );
                    }

                }

            }

        }

        return Arrays.stream(f[M - 1]).min().orElse(0);
    }

}
