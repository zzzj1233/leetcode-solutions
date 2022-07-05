package com.zzzj.dp;


/**
 * @author zzzj
 * @create 2021-11-02 16:22
 */
public class Leet53 {

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     */
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{1, -2, 3, -2}));
    }

    public static int maxSubArray(int[] nums) {
        int N = nums.length;

        int[] dp = new int[N];

        dp[0] = nums[0];

        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(max, dp[i]);
        }

        return max;
    }

}
