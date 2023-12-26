package com.zzzj.acw;

import java.util.Scanner;

/**
 * @author zzzj
 * @create 2023-12-04 15:57
 */
public class Q3417 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] w = new int[N];

        int V = 0;

        for (int i = 0; i < N; i++) {
            w[i] = scanner.nextInt();
            V += w[i];
        }

        boolean[][] dp = new boolean[N + 1][V + V + 1];

        dp[0][V] = true;

        for (int i = 1; i <= N; i++) {

            int num = w[i - 1];

            for (int j = -V; j <= V; j++) {

                dp[i][j + V] |= dp[i - 1][j + V];

                if (j - num >= -V)
                    dp[i][j + V] |= dp[i - 1][j + V - num];

                if (j + num <= V)
                    dp[i][j + V] |= dp[i - 1][j + V + num];

            }

        }

        int ans = 0;

        for (int i = 1; i <= V; i++) {
            if (dp[N][i + V])
                ans++;
        }

        System.out.println(ans);
    }

}
