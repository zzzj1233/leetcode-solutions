package com.zzzj.acw;

import java.util.Scanner;

public class Q1022 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 精灵球数量
        int N = scanner.nextInt();

        // 皮卡丘初始的体力值
        int M = scanner.nextInt();

        // 野生小精灵的数量
        int K = scanner.nextInt();

        int[][] w = new int[K][2];

        for (int i = 0; i < K; i++) {
            // 收服该小精灵需要的精灵球的数量
            w[i][0] = scanner.nextInt();
            // 收服过程中对皮卡丘造成的伤害
            w[i][1] = scanner.nextInt();
        }

        int[][] dp = new int[N + 1][M + 1];

        for (int i = 0; i < K; i++) {

            for (int x = N; x >= w[i][0]; x--) {

                for (int y = M; y > w[i][1]; y--) {

                    dp[x][y] = Math.max(
                            dp[x][y],
                            dp[x - w[i][0]][y - w[i][1]] + 1
                    );

                }

            }

        }

        System.out.println(dp[N][M]);

        for (int i = 1; i <= M; i++) {
            if (dp[N][i - 1] == dp[N][M]) {
                System.out.println(M - i);
                break;
            }
        }
    }

}
