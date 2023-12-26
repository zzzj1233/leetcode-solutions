package com.zzzj.acw;

import java.util.Scanner;

/**
 * @author zzzj
 * @create 2023-12-04 15:30
 */
public class Q3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int V = scanner.nextInt();

        int[] dp = new int[V + 1];

        for (int i = 0; i < N; i++) {

            int v = scanner.nextInt();

            int w = scanner.nextInt();

            for (int j = v; j <= V; j--)
                dp[j] = Math.max(dp[j], dp[j - v] + w);

        }

        System.out.println(dp[V]);
    }

}
