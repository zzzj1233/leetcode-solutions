package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-07-26 14:15
 */
public class Leet2707 {

    public static int massage(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];

        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[N - 1];
    }

}
