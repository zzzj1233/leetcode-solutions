package com.zzzj.acw;

import java.util.Scanner;

/**
 * @author zzzj
 * @create 2023-12-04 15:48
 */
public class Q3442 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int V = 40;

        int[] dp = new int[V + 1];

        dp[0] = 1;

        for (int i = 0; i < N; i++) {

            int v = scanner.nextInt();

            for (int j = V; j >= v; j--) {
                dp[j] += dp[j - v];
            }

        }

        System.out.println(dp[V]);
    }

}
