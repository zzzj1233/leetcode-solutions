package com.zzzj.acw;

import java.util.Arrays;
import java.util.Scanner;

public class Q1020 {

    public static void main(String[] args) {

        Scanner scanner;

        if ("zzzj".equals(System.getenv("USERNAME")))
            scanner = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("Q1020.txt"));
        else
            scanner = new Scanner(System.in);

        int M = 30;

        int N = 100;

        int m = scanner.nextInt();

        int n = scanner.nextInt();

        int K = scanner.nextInt();

        int[][] w = new int[K][3];

        for (int i = 0; i < K; i++) {
            w[i][0] = scanner.nextInt();
            w[i][1] = scanner.nextInt();
            w[i][2] = scanner.nextInt();
        }

        int[][] dp = new int[M][N];

        for (int i = 0; i < M; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        dp[0][0] = 0;

        for (int i = 0; i < K; i++) {

            for (int x = M - 1; x >= w[i][0]; x--) {

                for (int y = N - 1; y >= w[i][1]; y--) {

                    int val = dp[x - w[i][0]][y - w[i][1]];

                    if (val == Integer.MAX_VALUE)
                        continue;

                    dp[x][y] = Math.min(
                            dp[x][y],
                            val + w[i][2]
                    );

                }

            }

        }

        int ans = Integer.MAX_VALUE;

        for (int i = m; i < M; i++) {
            for (int j = n; j < N; j++) {
                ans = Math.min(ans, dp[i][j]);
            }
        }

        System.out.println(ans);
    }

}
