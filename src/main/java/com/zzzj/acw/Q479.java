package com.zzzj.acw;

import java.util.Scanner;

public class Q479 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner("5\n" +
                "5 7 1 2 10");

        int N = scanner.nextInt();

        int[] w = new int[N];

        for (int i = 0; i < N; i++)
            w[i] = scanner.nextInt();

        int[][] f = new int[N][N];

        int[][] p = new int[N][N];

        for (int len = 0; len < N; len++) {

            for (int left = 0; left + len < N; left++) {

                int right = left + len;

                if (len == 0) {
                    f[left][right] = w[left];
                    p[left][right] = left;
                } else {
                    for (int k = left; k <= right; k++) {

                        int ls = k - 1 < left ? 1 : f[left][k - 1];
                        int rs = k + 1 > right ? 1 : f[k + 1][right];

                        int s = ls * rs + w[k];

                        if (f[left][right] < s) {
                            f[left][right] = s;
                            p[left][right] = k;
                        }

                    }
                }

            }

        }

        System.out.println(f[0][N - 1]);

        preOrder(0, N - 1, p, w);
    }

    private static void preOrder(int left, int right, int[][] p, int[] w) {
        if (left > right)
            return;
        int node = p[left][right];
        System.out.print(node + 1 + " ");
        preOrder(left, node - 1, p, w);
        preOrder(node + 1, right, p, w);
    }

}
