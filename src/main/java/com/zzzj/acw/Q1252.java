package com.zzzj.acw;

import java.util.Scanner;

public class Q1252 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int M = scanner.nextInt();

        int V = scanner.nextInt();

        int[][] w = new int[N][2];

        for (int i = 0; i < N; i++) {
            w[i][0] = scanner.nextInt();
            w[i][1] = scanner.nextInt();
        }

        int[] parent = new int[N];

        for (int i = 0; i < N; i++)
            parent[i] = i;

        for (int i = 0; i < M; i++) {
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;

            int px = findParent(parent, x);
            int py = findParent(parent, y);

            if (px != py) {
                parent[px] = py;
                w[py][0] += w[px][0];
                w[py][1] += w[px][1];
            }

        }

        int[] dp = new int[V + 1];

        for (int i = 0; i < N; i++) {

            if (parent[i] != i)
                continue;

            for (int j = V; j >= w[i][0]; j--) {
                dp[j] = Math.max(
                        dp[j],
                        dp[j - w[i][0]] + w[i][1]
                );
            }

        }

        System.out.println(dp[V]);
    }

    private static int findParent(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

}
