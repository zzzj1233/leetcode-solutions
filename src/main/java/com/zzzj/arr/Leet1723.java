package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-31 14:44
 */
public class Leet1723 {

    public static void main(String[] args) {

        System.out.println(minimumTimeRequired(new int[]{1, 2, 4, 7, 8}, 2));

        System.out.println(minimumTimeRequired(new int[]{3, 2, 3}, 3));

    }

    public static int minimumTimeRequired(int[] jobs, int k) {

        int N = jobs.length;

        int limit = 1 << N;

        int[][] dp = new int[limit][k];

        int[] costs = new int[limit];

        for (int i = 0; i < limit; i++)
            costs[i] = times(jobs, i);

        for (int i = 0; i < limit; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        for (int stat = 0; stat < limit; stat++)
            dp[stat][0] = costs[stat];

        for (int i = 1; i < k; i++) {

            for (int stat = 0; stat < limit; stat++) {

                for (int preStat = 0; preStat < stat; preStat++) {

                    if ((stat | preStat) == stat) {

                        int jobStat = stat ^ preStat;

                        dp[stat][i] = Math.min(
                                dp[stat][i],
                                Math.max(
                                        dp[preStat][i - 1],
                                        costs[jobStat]
                                )
                        );
                    }
                }

            }

        }

        return dp[limit - 1][k - 1];
    }

    private static int times(int[] jobs, int stat) {
        int sum = 0;
        for (int i = 0; i < jobs.length; i++) {
            if ((stat & (1 << i)) != 0)
                sum += jobs[i];
        }
        return sum;
    }

}
