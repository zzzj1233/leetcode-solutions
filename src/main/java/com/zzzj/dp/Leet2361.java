package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-08-26 11:50
 */
public class Leet2361 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(minimumCosts(new int[]{11, 5, 13}, new int[]{7, 10, 6}, 3)));
    }

    public static long[] minimumCosts(int[] regular, int[] express, int expressCost) {
        int N = regular.length;

        long[] ans = new long[N];

        long[][] dp = new long[N][2];

        dp[0][0] = regular[0];
        dp[0][1] = expressCost + express[0];
        ans[0] = Math.min(dp[0][0], dp[0][1]);

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][0] + regular[i], dp[i - 1][1] + regular[i]);
            dp[i][1] = Math.min(dp[i - 1][0] + expressCost + express[i], dp[i - 1][1] + express[i]);
            ans[i] = Math.min(dp[i][0], dp[i][1]);
        }

        return ans;
    }

}
