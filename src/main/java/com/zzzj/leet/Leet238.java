package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-12-08 18:06
 */
public class Leet238 {

    /**
     * <p>
     * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,2,3,4]
     * <p>
     * 输出: [24,12,8,6]
     * <p>
     * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
     * <p>
     * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * <p>
     * 进阶：
     * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     * <p>
     */

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{-1, 1, 0, -3, 3})));
    }

    public static int[] productExceptSelf(int[] nums) {
        final int n = nums.length;

        int[] answer = new int[n];

        int[] L = new int[n];
        int[] R = new int[n];

        L[0] = nums[0];
        R[n - 1] = nums[n - 1];

        for (int i = 1; i < n; i++) {
            L[i] = nums[i] * L[i - 1];
        }

        for (int i = n - 2; i >= 0; i--) {
            R[i] = nums[i] * R[i + 1];
        }

        System.out.println(Arrays.toString(L));
        System.out.println(Arrays.toString(R));

        answer[0] = R[1];
        answer[n - 1] = L[n - 2];

        for (int i = 1; i < n - 1; i++) {
            answer[i] = L[i - 1] * R[i + 1];
        }

        return answer;
    }

}
