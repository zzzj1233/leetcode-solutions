package com.zzzj.acw;

import java.util.Arrays;
import java.util.Scanner;

public class Q1380 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner("5 2\n" +
                "1 3");

        int K = scanner.nextInt();

        int N = scanner.nextInt();

        int[] stamps = new int[N];

        int maxS = 0;

        for (int i = 0; i < N; i++) {
            stamps[i] = scanner.nextInt();
            maxS = Math.max(
                    maxS,
                    stamps[i]
            );
        }

        int V = maxS * K;

        int[] dp = new int[V + 1];

        Arrays.fill(dp, K + 10000);

        dp[0] = 0;

        for (int stamp : stamps) {

            for (int i = stamp; i <= V; i++) {

                // 最少需要多少张邮票?
                dp[i] = Math.min(
                        dp[i],
                        dp[i - stamp] + 1
                );

            }

        }

        for (int i = 1; i <= V; i++) {
            if (dp[i] > K) {
                System.out.println(i - 1);
                return;
            }
        }

        System.out.println(V);
    }

}
