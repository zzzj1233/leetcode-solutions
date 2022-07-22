package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-07-19 16:55
 */
public class Leet377 {

    public static int combinationSum4(int[] nums, int target) {
        int N = nums.length;

        int[][] dp = new int[N][target + 1];

        if (nums[0] < target && target % nums[0] == 0) {

            for (int i = 0; i < target; i++) {


            }

        }

        for (int i = 1; i < N; i++) {

        }

        return dp[N - 1][target];
    }

}
