package com.zzzj.dp.stock;

/**
 * @author zzzj
 * @create 2022-04-11 10:19
 */
public class Leet121 {

    public static int maxProfit(int[] prices) {
        int min = prices[0];
        int ans = 0;

        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, prices[i] - min);
        }

        return ans;
    }

}
