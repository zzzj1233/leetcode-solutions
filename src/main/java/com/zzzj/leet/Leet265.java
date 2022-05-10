package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-05-07 18:52
 */
public class Leet265 {

    public static int minCostII(int[][] costs) {
        int[] dp = new int[costs[0].length];

        for (int i = 0; i < costs[0].length; i++) {
            dp[i] = costs[0][i];
        }

        for (int i = 1; i < costs.length; i++) {
            int[] copyDp = Arrays.copyOfRange(dp, 0, dp.length);

            // int cost = costs[i][j];
            for (int j = 0; j < dp.length; j++) {
                // dp[j] = Math.min()
            }

        }

        int ans = dp[0];

        for (int i = 1; i < dp.length; i++) {
            ans = Math.min(ans, dp[i]);
        }

        return ans;
    }

}
