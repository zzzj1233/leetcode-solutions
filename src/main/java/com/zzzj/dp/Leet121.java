package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-10-29 17:32
 */
public class Leet121 {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public static int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            ans = Math.max(ans, dfs(prices, i));
        }
        return ans;
    }

    public static int dp(int[] prices) {
        int[] dp = new int[prices.length + 1];

        return dp[0];
    }

    public static int dfs(int[] prices, int buy) {
        int max = 0;
        for (int i = buy + 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - prices[buy]);
        }
        return max;
    }

}
