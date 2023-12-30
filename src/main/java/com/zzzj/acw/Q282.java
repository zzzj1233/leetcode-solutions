package com.zzzj.acw;

import java.util.Scanner;

public class Q282 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] stone = new int[N];

        for (int i = 0; i < N; i++)
            stone[i] = scanner.nextInt();

        int[][] dp = new int[N][N];

        int[] preSum = new int[N + 1];

        for (int i = 1; i <= N; i++)
            preSum[i] = preSum[i - 1] + stone[i - 1];

        for (int len = 2; len <= N; len++) {

            for (int left = 0, right = left + len - 1; right < N; left++, right = left + len - 1) {

                dp[left][right] = Integer.MAX_VALUE;

                // 分界线
                for (int k = left; k < right; k++) {

                    dp[left][right] = Math.min(
                            dp[left][right],
                            dp[left][k] + dp[k + 1][right] + (preSum[right + 1] - preSum[left])
                    );

                }

            }

        }

        System.out.println(dp[0][N - 1]);
    }

}
