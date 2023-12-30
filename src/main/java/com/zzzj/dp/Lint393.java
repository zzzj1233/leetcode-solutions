package com.zzzj.dp;

import java.util.Arrays;

public class Lint393 {

    public static void main(String[] args) {

        System.out.println(maxProfit(2, new int[]{4, 4, 6, 1, 1, 4, 2, 5}));

    }

    public static int maxProfit(int K, int[] prices) {

        int N = prices.length;

        int[] buy = new int[K + 1];

        int[] sell = new int[K + 1];

        Arrays.fill(buy, Integer.MIN_VALUE);

        for (int i = 1; i <= N; i++) {

            int p = prices[i - 1];

            for (int k = 1; k <= K; k++) {
                buy[k] = Math.max(
                        Math.max(
                                buy[k], buy[k - 1]
                        ),
                        sell[k - 1] - p
                );
            }

            for (int k = 1; k <= K; k++) {
                sell[k] = Math.max(
                        Math.max(
                                sell[k], sell[k - 1]
                        ),
                        buy[k] + p
                );
            }


        }

        return sell[K];
    }

}
