package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-12-12 14:35
 */
public class Leet188 {

    public static void main(String[] args) {

        System.out.println(maxProfit(2, new int[]{2, 4, 1}));

        System.out.println(maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));

    }

    public static int maxProfit(int K, int[] prices) {

        int N = prices.length;

        int[][] buy = new int[N + 1][K + 1];

        int[][] sell = new int[N + 1][K + 1];

        for (int i = 0; i <= N; i++) {
            Arrays.fill(buy[i], Integer.MIN_VALUE);
        }

        for (int i = 1; i <= N; i++) {

            for (int k = 1; k <= K; k++)
                buy[i][k] = Math.max(
                        Math.max(buy[i - 1][k], buy[i][k - 1]),
                        sell[i - 1][k - 1] - prices[i - 1]
                );

            for (int k = 1; k <= K; k++)
                sell[i][k] = Math.max(
                        Math.max(sell[i - 1][k], sell[i][k - 1]),
                        buy[i][k] + prices[i - 1]
                );

        }

        return sell[N][K];
    }

}
