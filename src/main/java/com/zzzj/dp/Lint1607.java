package com.zzzj.dp;

import java.util.Arrays;

public class Lint1607 {

    public static void main(String[] args) {


        System.out.println(profitableSchemes(5, 3, new int[]{2, 2}, new int[]{2, 3}));

        System.out.println(profitableSchemes(10, 5, new int[]{2, 3, 5}, new int[]{6, 7, 8}));

    }

    public static int profitableSchemes(int g, int p, int[] group, int[] profit) {

        int N = group.length;

        int[][] dp = new int[g + 1][p + 1];

        dp[0][0] = 1;

        for (int i = 0; i < N; i++) {

            int personCnt = group[i];

            int gain = profit[i];

            for (int j = g; j >= personCnt; j--) {

                for (int k = 0; k <= p; k++) {
                    dp[j][Math.min(p, k + gain)] += dp[j - personCnt][k];
                }

            }

        }

          // System.out.println("dp = " + Arrays.deepToString(dp));

         return Arrays.stream(dp).mapToInt(item -> item[p]).sum();

//        return dp[g][p];
    }

}
