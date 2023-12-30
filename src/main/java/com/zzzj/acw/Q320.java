package com.zzzj.acw;

import java.util.Scanner;

public class Q320 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner("7\n" +
                "23 17 212 113 71 301 33 ");

        int N = scanner.nextInt();

        int[] jewelry = new int[N << 1];

        for (int i = 0; i < N; i++) {
            jewelry[i] = scanner.nextInt();
            jewelry[i + N] = jewelry[i];
        }

        int[][] f = new int[N << 1][N << 1];

        for (int len = 1; len <= N; len++) {

            for (int left = 0; left < f.length; left++) {

                int right = left + len;

                if (right >= f.length)
                    break;

                for (int k = left + 1; k < right; k++) {
                    f[left][right] = Math.max(
                            f[left][right],
                            f[left][k] + f[k][right] + (jewelry[k] * jewelry[left] * jewelry[right])
                    );
                }

            }

        }

        int max = 0;

        for (int i = 0; i < N; i++)
            max = Math.max(max, f[i][i + N]);

        System.out.println(max);
    }

}
