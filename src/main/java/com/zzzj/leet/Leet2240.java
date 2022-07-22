package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-07-22 15:07
 */
public class Leet2240 {

    public static void main(String[] args) {
        System.out.println(waysToBuyPensPencils(20, 10, 5));
    }

    public static long waysToBuyPensPencils(int total, int cost1, int cost2) {
        // total
        long[] dp = new long[total + 1];

        // 第一种方案,啥也不买
        dp[0] = 1;

        for (int i = cost1; i <= total; i++) {
            dp[i] += dp[i - cost1];
        }

        for (int i = cost2; i <= total; i++) {
            dp[i] += dp[i - cost2];
        }

        return Arrays.stream(dp).sum();
    }


}
