package com.zzzj.dp.stock;

/**
 * @author zzzj
 * @create 2022-04-11 10:21
 */
public class Leet122 {


    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }

    public static int maxProfit(int[] prices) {

        int N = prices.length;

        int[][] dp = new int[N][2];

        // dp[i][0] = 不持有的最大收益
        // dp[i][1] = 持有的最大收益

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < N; i++) {
            // max(继续不持有,(之前买了股票,卖了当前股票))
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);

            // max(继续持有之前的股票,(之前没买股票,买了当前的股票))
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[N - 1][0];
    }

}
