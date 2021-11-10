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
        System.out.println(dynamicPlanning(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    private static int dynamicPlanning(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];

        dp[n - 1] = nums[n - 1];

        int max = Math.max(nums[n - 1], 0);

        for (int i = n - 2; i >= 0; i--) {
            // 三种分支
            // 1. 不要当前的值,那么max = total(previous)
            // 2. 要当前的值,但是不要前面的值
            // 3. 要当前的值,也要前面的值
            int val1 = dp[i + 1] + nums[i];
            int val2 = nums[i];

            dp[i] = Math.max(val1, val2);

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static int maxSubArray(int[] nums) {
        return violentRecursion(nums, 0, 0);
    }

    private static int violentRecursion(int[] nums, int i, int total) {
        if (i >= nums.length) {
            return total;
        }

        // 不要total
        int val1 = violentRecursion(nums, i + 1, nums[i]);
        int val2 = violentRecursion(nums, i + 1, total + nums[i]);

        int curVal = Math.max(nums[i] + total, Math.max(total, nums[i]));

        return Math.max(val1, Math.max(val2, curVal));
    }

}
