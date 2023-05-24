package com.zzzj.hot;

/**
 * @author zzzj
 * @create 2022-04-19 17:44
 */
public class Leet198 {

    public static void main(String[] args) {
        rob(new int[]{2, 1, 1, 2});
    }

    public static int rob(int[] nums) {

        int N = nums.length;

        if (N == 1) {
            return nums[0];
        }

        if (N == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[N];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[N - 1];
    }

}
