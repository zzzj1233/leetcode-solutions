package com.zzzj.acw;

import java.util.Scanner;

public class Q8 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // N件物品
        int N = scanner.nextInt();

        // 容量是V的背包
        int V = scanner.nextInt();

        // 背包能承受的最大重量是M
        int M = scanner.nextInt();

        int[][] w = new int[N][3];

        for (int i = 0; i < N; i++) {
            // 体积
            w[i][0] = scanner.nextInt();
            // 重量
            w[i][1] = scanner.nextInt();
            // 价值
            w[i][2] = scanner.nextInt();
        }

        int[][] dp = new int[V + 1][M + 1];

        for (int i = 0; i < N; i++) {

            for (int x = V; x >= w[i][0]; x--) {

                for (int y = M; y >= w[i][1]; y--) {
                    dp[x][y] = Math.max(
                            dp[x][y],
                            dp[x - w[i][0]][y - w[i][1]] + w[i][2]
                    );
                }

            }

        }

        System.out.println(dp[V][M]);
    }

}
