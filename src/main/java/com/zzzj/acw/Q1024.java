package com.zzzj.acw;

import java.util.Scanner;

public class Q1024 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int V = scanner.nextInt();

        int N = scanner.nextInt();

        int[] boxes = new int[N];

        for (int i = 0; i < N; i++)
            boxes[i] = scanner.nextInt();

        int[] dp = new int[V + 1];

        for (int i = 0; i < N; i++) {

            for (int j = V; j >= boxes[i]; j--) {
                dp[j] = Math.max(
                        dp[j],
                        dp[j - boxes[i]] + boxes[i]
                );
            }

        }

        int max = 0;

        for (int i = 0; i <= V; i++)
            max = Math.max(max, dp[i]);

        System.out.println(V - max);
    }

}
