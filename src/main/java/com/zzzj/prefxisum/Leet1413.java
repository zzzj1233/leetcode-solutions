package com.zzzj.prefxisum;

/**
 * @author zzzj
 * @create 2021-12-09 15:56
 */
public class Leet1413 {

    /**
     * <p>
     * 给你一个整数数组 nums。你可以选定任意的正数 startValue 作为初始值。
     * <p>
     * 你需要从左到右遍历 nums数组，并将 startValue 依次累加上nums数组中的值。
     * <p>
     * 请你在确保累加和始终大于等于 1 的前提下，选出一个最小的正数作为 startValue 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [-3,2,-3,4,2]
     * 输出：5
     * 解释：如果你选择 startValue = 4，在第三次累加时，和小于 1 。
     * 累加求和
     * startValue = 4 | startValue = 5 | nums
     * (4 -3 ) = 1  | (5 -3 ) = 2    |  -3
     * (1 +2 ) = 3  | (2 +2 ) = 4    |   2
     * (3 -3 ) = 0  | (4 -3 ) = 1    |  -3
     * (0 +4 ) = 4  | (1 +4 ) = 5    |   4
     * (4 +2 ) = 6  | (5 +2 ) = 7    |   2
     * 示例 2：
     * <p>
     * 输入：nums = [1,2]
     * 输出：1
     * 解释：最小的 startValue 需要是正数。
     * 示例 3：
     * <p>
     * 输入：nums = [1,-2,-3]
     * 输出：5
     */

    public static void main(String[] args) {
        System.out.println(minStartValue(new int[]{-3, 2, -3, 4, 2}));
    }

    public static int minStartValue(int[] nums) {
        final int n = nums.length;

        if (n == 1) {
            return nums[0] > 0 ? 0 : -nums[0];
        }

        int[] sum = new int[n];

        int answer = 1;

        for (int i = 0; i < n; i++) {
            sum[i] = i - 1 >= 0 ? sum[i - 1] + nums[i] : nums[i];
            if (sum[i] <= 0) {
                answer = Math.max(answer, -sum[i] + 1);
            }
        }

        return answer;
    }

}
