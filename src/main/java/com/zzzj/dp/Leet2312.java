package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-08-26 16:40
 */
public class Leet2312 {

    public static void main(String[] args) {
        System.out.println(sellingWood(3, 5, LeetUtils.convertInts("[[1,4,2],[2,2,7],[2,1,3]]")));
    }

    public static long sellingWood(int m, int n, int[][] prices) {

        int[][] pr = new int[m + 1][n + 1];

        for (int[] price : prices) {
            pr[price[0]][price[1]] = price[2];
        }

        long[][] dp = new long[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                dp[i][j] = pr[i][j];

                for (int k = 1; k <= i; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - k][j] + dp[k][j]);
                }

                for (int k = 1; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - k] + dp[i][k]);
                }

            }
        }

        return dp[m][n];
    }

}
