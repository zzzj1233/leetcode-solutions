package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-30 14:22
 */
public class Leet1708 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(largestSubarray(new int[]{1, 4, 5, 2, 3}, 3)));
        System.out.println(Arrays.toString(largestSubarray(new int[]{1, 4, 5, 2, 3}, 4)));
    }

    public static int[] largestSubarray(int[] nums, int k) {
        // 长度为K的最大子数组

        int max = 0;

        for (int i = 1; i < nums.length - k + 1; i++) {
            if (nums[i] > nums[max]) {
                max = i;
            }
        }

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            ans[i] = nums[i + max];
        }

        return ans;
    }

}
