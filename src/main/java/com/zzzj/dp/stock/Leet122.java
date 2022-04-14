package com.zzzj.dp.stock;

/**
 * @author zzzj
 * @create 2022-04-11 10:21
 */
public class Leet122 {

    public static int maxProfit(int[] prices) {

        int ans = 0;

        for (int i = 1; i < prices.length; i++) {
            int sub = prices[i] - prices[i - 1];
            if (sub > 0) {
                ans += sub;
            }
        }

        return ans;
    }

}
