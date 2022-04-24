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
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

}
