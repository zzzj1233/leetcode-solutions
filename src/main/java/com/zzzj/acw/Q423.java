package com.zzzj.acw;

import java.util.Scanner;

public class Q423 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();

        int M = scanner.nextInt();

        int[][] w = new int[M][2];

        for (int i = 0; i < M; i++) {
            w[i][0] = scanner.nextInt();
            w[i][1] = scanner.nextInt();
        }

        System.out.println(solution(T, M, w));
    }

    private static int solution(int T, int M, int[][] w) {

        int[] dp = new int[T + 1];

        for (int i = 0; i < M; i++) {

            for (int j = T; j >= w[i][0]; j--) {

                dp[j] = Math.max(
                        dp[j],
                        dp[j - w[i][0]] + w[i][1]
                );

            }
        }

        return dp[T];
    }


}
