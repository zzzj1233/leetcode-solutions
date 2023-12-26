package com.zzzj.acw;

import java.util.Scanner;

/**
 * @author zzzj
 * @create 2023-12-04 15:30
 */
public class Q4 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int V = scanner.nextInt();

        int[][] dp = new int[N + 1][V + 1];

        for (int i = 1; i <= N; i++) {

            int v = scanner.nextInt();

            int w = scanner.nextInt();

            // 次数
            int s = scanner.nextInt();

            for (int j = 0; j <= s && j * v <= V; j++) {

                for (int k = j * v; k <= V; k++) {
                    dp[i][k] = Math.max(
                            Math.max(dp[i][k], dp[i - 1][k]),
                            dp[i - 1][k - j * v] + j * w
                    );
                }

            }

        }

        System.out.println(dp[N][V]);
    }

}
