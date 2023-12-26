package com.zzzj.acw;

import java.util.Scanner;

/**
 * @author zzzj
 * @create 2023-12-04 14:31
 */
public class Q1365 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int sum = ((N + 1) * N) / 2;

        if (sum % 2 != 0){
            System.out.println(0);
            return;
        }

        int half = sum / 2;

        long[][] dp = new long[N + 1][half + 1];

        for (int i = 0; i <= N; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= N; i++) {

            for (int j = 1; j <= half; j++) {

                dp[i][j] += dp[i - 1][j];

                if (i <= j)
                    dp[i][j] += dp[i - 1][j - i];

            }

        }

        System.out.println(dp[N][half] / 2);
    }

}
