package com.zzzj.dp.stock;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-04-11 11:56
 */
public class Leet123 {

    public static void main(String[] args) {

//        System.out.println(maxProfit(new int[]{0, 4, 3, 6, 9}));
//
//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            int[] arr = ArrayUtil.generateArray(500, 0, 1000);
            if (maxProfit(arr) != right(arr)) {
                System.out.println(Arrays.toString(arr));
                System.out.println(maxProfit(arr));
                System.out.println(right(arr));
                return;
            }
        }
        System.out.println("Ok");
    }

    // [3,3,5,0,0,3,1,4]
    // 可以交易两次
    public static int maxProfit2(int[] prices) {
        int N = prices.length;

        int[] dp = new int[N];

        int min = prices[0];

        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        int[] max = new int[N];

        max[N - 1] = prices[N - 1];

        for (int i = N - 2; i >= 0; i--) {
            max[i] = Math.max(prices[i], max[i + 1]);
        }

        int ans = 0;

        for (int i = 1; i < N; i++) {
            ans = Math.max(ans, dp[i] + max[i] - prices[i]);
        }

        return ans;
    }

    public static int maxProfit(int[] prices) {
        int N = prices.length;

        int min = prices[0];

        int ans = 0;

        int[] max = new int[N];

        max[N - 1] = prices[N - 1];

        for (int i = N - 2; i >= 0; i--) {
            max[i] = Math.max(prices[i], max[i + 1]);
        }

        int preMax = 0;

        for (int i = 1; i < N; i++) {
            preMax = Math.max(preMax, prices[i] - min);
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, preMax + max[i] - prices[i]);
        }

        return ans;
    }

    public static int right(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }

}
