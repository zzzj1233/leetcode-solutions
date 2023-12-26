package com.zzzj.acw;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zzzj
 * @create 2023-12-04 17:37
 */
public class Q4700 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int X = scanner.nextInt();

        int[] dp = new int[X + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 0; i < N; i++) {

            int v = scanner.nextInt();

            for (int j = X; j >= 0; j--) {

                dp[j] = Math.min(
                        dp[j],
                        (dp[Math.max(0, j - v)] == Integer.MAX_VALUE) ? Integer.MAX_VALUE : (dp[Math.max(0, j - v)] + v)
                );

            }

        }

        System.out.println(dp[X]);

    }

}
