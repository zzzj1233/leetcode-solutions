package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-10-29 11:38
 */
public class ZeroOnePackage {

    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        System.out.println(testWeightBagProblem(weight, value, bagSize));
    }

    public static int testWeightBagProblem(int[] weight, int[] value, int bagSize) {
        final int n = weight.length;

        int[][] dp = new int[n][bagSize];

        for (int i = 1; i < n; i++) {
            dp[0][i] = n - weight[0] >= 0 ? value[0] : 0;
        }

        for (int i = 1; i < n; i++) {

            for (int j = 1; j < bagSize; j++) {
                if (weight[i] + weight[i - 1] <= j + 1) {
                    dp[i][j] = dp[i - 1][j] + value[i];
                } else if (weight[i] <= j + 1) {
                    dp[i][j] = Math.max(value[i], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }

        }

        return dp[n - 1][bagSize - 1];
    }

}
