package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-10-29 17:51
 */
public class Leet122 {

    public static int maxProfit(int[] prices) {

        int ans = 0;
        int min = prices[0];

        for (int i = 1; i < prices.length; i++) {
            ans = Math.max(ans, prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        return ans;
    }

}
