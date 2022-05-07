package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-07 18:37
 */
public class Leet256 {

    public static int minCost(int[][] costs) {
        int N = costs.length;

        int val1 = costs[0][0];
        int val2 = costs[0][1];
        int val3 = costs[0][2];

        for (int i = 1; i < N; i++) {
            int oldVal1 = val1;

            int oldVal2 = val2;

            int oldVal3 = val3;

            val1 = costs[i][0] + Math.min(oldVal2, oldVal3);
            val2 = costs[i][1] + Math.min(oldVal1, oldVal3);
            val3 = costs[i][2] + Math.min(oldVal1, oldVal2);
        }

        return Math.min(val1, Math.min(val2, val3));
    }

}
