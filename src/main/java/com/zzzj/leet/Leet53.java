package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-06 14:25
 */
public class Leet53 {

    //  子数组的最大和
    public static int maxSubArray(int[] nums) {
        int max = 0;
        int cur = 0;

        // 贪心
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i];
            if (cur < 0) {
                cur = 0;
            } else {
                max = Math.max(max, cur);
            }
        }

        return max;
    }

    // [-2,1]
    public static int dp(int[] nums) {
        int[] dp = new int[nums.length];

        dp[0] = nums[0];

        int max = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }

        return max;
    }

}
