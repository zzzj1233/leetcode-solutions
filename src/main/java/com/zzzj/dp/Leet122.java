package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-10-29 17:51
 */
public class Leet122 {

    public static void main(String[] args) {
        System.out.println(dp(new int[]{7, 1, 5, 3, 6, 4}));
    }

    private static int dp(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        dp[0][0] = prices[0];

        // i = 第N天
        // j[0] = 买了的收益
        // j[1] = 卖了的收益

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][1], prices[i] - dp[i - 1][0]);
        }

        return dp[n - 1][1];
    }

    public static int maxProfit(int[] prices) {
        return violentRecursion2(prices, -1, 0);
    }

    private static int violentRecursion(int[] prices, int buy, int i, int total) {
        if (i >= prices.length) {
            return total;
        }

        // 可以买可以不买
        if (buy == -1) {
            return Math.max(violentRecursion(prices, -1, i + 1, total), violentRecursion(prices, prices[i], i + 1, total));
        } else {
            // 买了之后可以卖可以不卖
            return Math.max(violentRecursion(prices, buy, i + 1, total), violentRecursion(prices, -1, i + 1, total + prices[i] - buy));
        }
    }

    private static int violentRecursion2(int[] prices, int buyIndex, int i) {
        if (buyIndex >= prices.length || i >= prices.length) {
            return 0;
        }
        // 可以买可以不买
        if (buyIndex == -1) {
            int val1 = violentRecursion2(prices, i, i + 1);
            int val2 = violentRecursion2(prices, buyIndex, i + 1);
            return Math.max(val1, val2);
        } else {
            // 买了之后可以卖可以不卖
            int val1 = prices[i] - prices[buyIndex] + violentRecursion2(prices, -1, i + 1);
            int val2 = violentRecursion2(prices, buyIndex, i + 1);
            return Math.max(val1, val2);
        }
    }

}
