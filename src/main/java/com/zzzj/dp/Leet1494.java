package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-07-06 17:16
 */
public class Leet1494 {

    public static int minNumberOfSemesters(int n, int[][] relations, int k) {

        int[] need = new int[1 << n];

        for (int[] edge : relations) {
            need[1 << edge[1]] |= 1 << edge[0];
        }

        int[] dp = new int[1 << n];

        OUTER:
        for (int stat = 0; stat < dp.length; stat++) {

            // 还有前置的课程没有学完
            for (int j = 0; j < n; j++)
                if ((stat & (1 << j)) != 0 && (need[1 << j] & stat) != need[1 << j]) continue OUTER;

            // 计算这个状态需要多少个课时

            // 当前这个状态依赖的状态是什么

        }

        return dp[dp.length - 1];
    }


}
