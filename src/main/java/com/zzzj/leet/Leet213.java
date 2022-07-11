package com.zzzj.leet;


import com.zzzj.util.ArrayUtil;

/**
 * @author zzzj
 * @create 2021-10-28 14:15
 */
public class Leet213 {

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     * <p>
     * <p>
     * <p>
     * 示例1：
     * <p>
     * 输入：nums = [2,3,2]
     * 输出：3
     * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     * <p>
     * 示例 2：
     * <p>
     * 输入：nums = [1,2,3,1]
     * 输出：4
     * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     * 偷窃到的最高金额 = 1 + 3 = 4 。
     */
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            final int[] arr = ArrayUtil.generateArray(1000, 0, 100);
            if (rob(arr) != right(arr)) {
                System.out.println("Error");
                return;
            }
        }
        System.out.println("Ok");
    }

    // 最后一个和第一个互斥
    // 2,3,2
    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        if (nums.length == 3) {
            return Math.max(nums[0], Math.max(nums[1], nums[2]));
        }

        int N = nums.length;

        int[] dp = new int[N - 1];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < N - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        int[] dp2 = new int[N];

        dp2[1] = nums[1];
        dp2[2] = Math.max(nums[1], nums[2]);

        for (int i = 3; i < N; i++) {
            dp2[i] = Math.max(dp2[i - 2] + nums[i], dp2[i - 1]);
        }

        return Math.max(dp[N - 2], dp2[N - 1]);
    }

    public static int right(int[] nums) {
        int end = nums.length - 1;
        if (end == 0) return nums[0];
        if (end == 1) return Math.max(nums[0], nums[1]);
        return Math.max(dp(nums, 0, end - 1), dp(nums, 1, end));
    }

    private static int dp(int[] nums, int start, int end) {
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }

}
