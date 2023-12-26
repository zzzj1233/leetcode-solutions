package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-11-13 16:16
 */
public class Leet2188 {

    public static void main(String[] args) {

        System.out.println(minimumFinishTime(
                LeetUtils.convertInts("[[96,3],[68,2],[53,4],[60,8],[29,8],[96,8],[31,10],[5,4],[49,6],[54,7],[90,7],[7,7],[97,2],[50,9],[34,2],[89,7],[51,7],[73,3],[42,4],[24,7],[99,3],[34,10],[33,9],[45,7],[32,2],[59,2],[76,3],[10,6],[78,7],[19,4],[65,2],[30,9],[10,5],[84,5],[62,4],[87,2],[59,8],[29,5],[40,4],[76,6]]"),
                15,
                71
        ));

        System.exit(0);

        System.out.println(minimumFinishTime(
                LeetUtils.convertInts("[[2,3],[3,4]]"),
                5,
                4
        ));

        System.out.println(minimumFinishTime(
                LeetUtils.convertInts("[[1,10],[2,2],[3,4]]"),
                6,
                5
        ));

    }

    public static int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {

        int[] minCost = new int[numLaps + 1];

        Arrays.fill(minCost, Integer.MAX_VALUE);

        for (int[] tire : tires) {

            long cost = 0;

            for (int i = 0; i <= numLaps; i++) {

                cost += (long) (tire[0] * Math.pow(tire[1], i));

                if (cost >= Integer.MAX_VALUE)
                    break;

                minCost[i] = (int) Math.min(
                        minCost[i],
                        cost
                );

            }

        }

        long[] dp = new long[numLaps + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[1] = minCost[0];

        for (int i = 2; i <= numLaps; i++) {

            long prev = dp[i - 1];

            for (int j = i; j <= numLaps; j++) {
                dp[j] = Math.min(
                        Math.min(dp[j], minCost[j - 1]),
                        prev + minCost[j - i] + changeTime
                );

            }

        }

        // System.out.println("dp = " + Arrays.toString(dp));

        return (int) dp[numLaps];
    }

}
