package com.zzzj.acw;

import java.util.Scanner;

public class Q9 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int V = scanner.nextInt();

        int[][][] w = new int[N][][];

        for (int i = 0; i < N; i++) {

            int M = scanner.nextInt();

            w[i] = new int[M][2];

            for (int j = 0; j < M; j++) {
                w[i][j][0] = scanner.nextInt();
                w[i][j][1] = scanner.nextInt();
            }

        }

        int[] dp = new int[V + 1];

        for (int i = 1; i <= N; i++) {

            int[][] group = w[i - 1];

            for (int v = V; v >= 0; v--) {

                for (int[] item : group) {

                    int weight = item[0];

                    int value = item[1];

                    if (v >= weight)
                        dp[v] = Math.max(
                                dp[v],
                                dp[v - weight] + value
                        );

                }

            }

        }

        System.out.println(dp[V]);
    }

}
