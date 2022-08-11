package com.zzzj.bat.lesson1;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-08-06 14:13
 */
public class Question7 {

    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{43, 1, 49, 22, 41, 1, 11, 1, 24, 10, 26, 49, 33, 4, 20, 19, 44, 42, 2, 37}, 17));

        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }


    public static int findTargetSumWays(int[] nums, int target) {
        int N = nums.length;

        if (N == 0) {
            return 0;
        }

        int sum = Arrays.stream(nums).sum();

        if (Math.abs(target) > sum) {
            return 0;
        }

        int end = (sum << 1) + 1;

        int[][] dp = new int[N][end + 1];

        if (nums[0] == 0) {
            dp[0][sum] = 2;
        } else {
            dp[0][sum - nums[0]] = 1;
            dp[0][nums[0] + sum] = 1;
        }


        for (int i = 1; i < N; i++) {

            int num = nums[i];

            for (int j = 0; j <= end; j++) {

                dp[i][j] = pick(dp, i - 1, j + num) + pick(dp, i - 1, j - num);

            }

        }

        return dp[N - 1][target + sum];
    }

    public static void set(int[] dp, int j, int value, int end) {
        if (j < 0) {
            return;
        }
        if (j > end) {
            return;
        }
        dp[j] = value;
    }

    public static int pick(int[][] dp, int i, int j) {
        if (j < 0) {
            return 0;
        }
        if (j >= dp[i].length) {
            return 0;
        }
        return dp[i][j];
    }

}
