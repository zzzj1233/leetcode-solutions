package com.zzzj.acw;

import java.util.Scanner;

public class Q1068 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int M = scanner.nextInt();

        int N = M << 1;

        int[] stone = new int[N];

        for (int i = 0; i < M; i++) {
            int num = scanner.nextInt();
            stone[i] = num;
            stone[i + M] = num;
        }

        int[][] max = new int[N][N];

        long[][] min = new long[N][N];

        int[] preSum = new int[N + 1];

        for (int i = 1; i <= N; i++)
            preSum[i] = preSum[i - 1] + stone[i - 1];

        for (int len = 1; len < M; len++) {

            for (int left = 0; left < N; left++) {

                int right = left + len;

                if (right >= N)
                    break;

                // System.out.printf("left = %d , right = %d %n", left, right);

                min[left][right] = Integer.MAX_VALUE;

                for (int k = left; k < right; k++) {

                    max[left][right] = Math.max(
                            max[left][right],
                            max[left][k] + max[k + 1][right] + preSum[right + 1] - preSum[left]
                    );

                    min[left][right] = Math.min(
                            min[left][right],
                            min[left][k] + min[k + 1][right] + preSum[right + 1] - preSum[left]
                    );

                }

            }

        }

        int maxV = Integer.MIN_VALUE;

        int minV = Integer.MAX_VALUE;

        for (int i = 0; i < M; i++) {
            maxV = Math.max(
                    maxV,
                    max[i][i + M - 1]
            );
            minV = (int) Math.min(
                    minV,
                    min[i][i + M - 1]
            );
        }

        System.out.println(minV);
        System.out.println(maxV);
    }

}
