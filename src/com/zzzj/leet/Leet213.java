package com.zzzj.leet;


import java.util.Arrays;

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
        for (int i = 0; i < 5; i++) {
            int[] ints = LeetUtils.newArray(10);
            System.out.println(Arrays.toString(ints));
            System.out.println(rob(ints));
            System.out.println("==================\n");
        }
        System.out.println(rob(new int[]{1}));
    }

    public static int rob(int[] nums) {
        int n = nums.length;

        if (n == 0) {
            return 0;
        }

        if (n == 1){
            return nums[0];
        }

        int[] memo = new int[n + 1];
        memo[n] = 0;
        memo[n - 1] = 0;

        for (int i = n - 2; i >= 0; i--) {
            int sum = nums[i] + memo[i + 2];
            memo[i] = Math.max(sum, memo[i + 1]);
        }

        int ret = memo[0];
        memo[n - 1] = nums[n - 1];
        for (int i = n - 2; i > 0; i--) {
            int sum = nums[i] + memo[i + 2];
            memo[i] = Math.max(sum, memo[i + 1]);
        }

        return Math.max(ret, memo[1]);
    }

}
