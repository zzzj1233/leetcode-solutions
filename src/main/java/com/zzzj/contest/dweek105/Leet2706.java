package com.zzzj.contest.dweek105;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-07-31 15:37
 */
public class Leet2706 {

    public static int buyChoco(int[] prices, int money) {

        Arrays.sort(prices);

        if (money >= prices[0] + prices[1])
            return money - prices[0] - prices[1];

        return money;
    }

}
