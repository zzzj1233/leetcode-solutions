package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-06-17 18:04
 */
public class Leet813 {

    public static void main(String[] args) {
        System.out.println(largestSumOfAverages(new int[]{9, 1, 2, 3, 9}, 3));
        System.out.println(largestSumOfAverages(new int[]{4, 1, 7, 5, 6, 2, 3}, 4));
        System.out.println(largestSumOfAverages(new int[]{1, 2, 3, 4, 5, 6, 7}, 4));
    }

    public static double largestSumOfAverages(int[] nums, int k) {
        // 必须分为K个子数组
        int N = nums.length;

        // dp[i][j] = 当前i在第j个分组的情况下
        double[][] dp = new double[N][k];

        double[] preSum = new double[N + 1];

        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < N; i++) {
            dp[i][0] = (preSum[i + 1] - preSum[0]) / (i + 1);
        }


        for (int i = 1; i < nums.length; i++) {

            for (int j = 1; j < k; j++) {
                // 0 ~ i 划分到j个数组中的平均值

                double max = 0;

                for (int l = 0; l < i; l++) {
                    max = Math.max(max, dp[l][j - 1] + ((preSum[i + 1] - preSum[l + 1]) / (i - l)));
                }

                dp[i][j] = max;
            }

        }

        return dp[N - 1][k - 1];
    }


}
