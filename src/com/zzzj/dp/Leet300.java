package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-11-01 11:48
 */
public class Leet300 {

    /**
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * <p>
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * 示例 2：
     * <p>
     * 输入：nums = [0,1,0,3,2,3]
     * 输出：4
     * 示例 3：
     * <p>
     * 输入：nums = [7,7,7,7,7,7,7]
     * 输出：1
     */
    public static void main(String[] args) {
        System.out.println(solution(new int[]{16, 12, 13, 14, 15, 8, 9}));
    }

    public static int solution(int[] nums) {
        int[] dp = new int[nums.length];

        dp[0] = 1;

        int maxans = 1;

        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }

        return maxans;
    }

    public static int lengthOfLIS(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int val = maxLength(nums, i, Integer.MIN_VALUE);
            max = Math.max(max, val);
        }
        return max;
    }

    private static int maxLength(int[] nums, int i, int cur) {
        if (i >= nums.length) {
            return 0;
        }
        if (nums[i] > cur) {
            return 1 + maxLength(nums, i + 1, nums[i]);
        } else {
            return maxLength(nums, i + 1, cur);
        }

    }

}
