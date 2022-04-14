package com.zzzj.dp.stock;

/**
 * @author zzzj
 * @create 2022-04-11 11:56
 */
public class Leet123 {

    public static void main(String[] args) {
//        System.out.println(maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println(maxProfit(new int[]{1, 2, 3, 4, 5}));
    }

    // [3,3,5,0,0,3,1,4]
    // 可以买两次
    public static int maxProfit(int[] prices) {
        int min = prices[0];
        // 卖出一次的收益
        int onceProfit = 0;
        int ans = 0;

        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);

            if (prices[i] - min > onceProfit) {
                onceProfit = prices[i] - min;
                min = prices[i];
                continue;
            }

            ans = Math.max(ans, Math.max(onceProfit, onceProfit + prices[i] - min));
        }

        return ans;
    }


}
