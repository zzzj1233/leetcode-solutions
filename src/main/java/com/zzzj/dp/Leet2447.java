package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-04-08 11:29
 */
public class Leet2447 {

    public static void main(String[] args) {
        System.out.println(minCost(LeetUtils.convertInts("[[17,2,17],[16,16,5],[14,3,19]]")));
        System.out.println(minCost(LeetUtils.convertInts("[[7,6,2]]")));
    }

    public static int minCost(int[][] costs) {
        // red , blue , green
        return dp(costs);
    }

    public static int dp(int[][] costs) {
        int[][] dp = new int[costs.length][3];

        for (int i = 0; i < 3; i++) {
            dp[costs.length - 1][i] = costs[costs.length - 1][i];
        }

        for (int i = costs.length - 2; i >= 0; i--) {

            for (int j = 0; j < costs[i].length; j++) {
                int min = Integer.MAX_VALUE;
                int cos = costs[i][j];

                for (int k = 0; k < 3; k++) {
                    if (k != j) {
                        min = Math.min(min, dp[i + 1][k]);
                    }
                }

                dp[i][j] = cos + min;
            }

        }

        return Math.min(dp[0][0], Math.min(dp[0][1], dp[0][2]));
    }

    public static int dfs(int[][] costs, int i, int j) {
        if (i == costs.length) {
            return 0;
        }

        int[] cost = costs[i];

        int min = Integer.MAX_VALUE;

        for (int k = 0; k < cost.length; k++) {
            if (k != j) {
                int cos = cost[k];
                min = Math.min(min, cos + dfs(costs, i + 1, k));
            }
        }

        return min;
    }

}
