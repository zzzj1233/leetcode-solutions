package com.zzzj.acw;

import java.util.Scanner;

/**
 * @author zzzj
 * @create 2023-12-04 15:24
 */
public class Q426 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int V = scanner.nextInt();

        int N = scanner.nextInt();

        int[] dp = new int[V + 1];

        for (int i = 0; i < N; i++) {

            int v = scanner.nextInt();

            int w = v * scanner.nextInt();

            for (int j = V; j >= v; j--) {
                dp[j] = Math.max(dp[j], dp[j - v] + w);
            }

        }

        System.out.println(dp[V]);
    }

}
