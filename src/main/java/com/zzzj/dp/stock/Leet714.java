package com.zzzj.dp.stock;


/**
 * @author zzzj
 * @create 2022-04-11 10:19
 */
public class Leet714 {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
        System.out.println(maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3));
    }

    public static int maxProfit(int[] prices, int fee) {
        int profit = 0;

        int min = prices[0] + fee;

        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i] + fee);
            if (prices[i] - min > 0) {
                profit += prices[i] - min;
                min = prices[i];
            }
        }

        return profit;
    }


}
