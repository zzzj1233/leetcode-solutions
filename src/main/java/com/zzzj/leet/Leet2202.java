package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-30 15:10
 */
public class Leet2202 {

    public static void main(String[] args) {
        System.out.println(maximumTop(new int[]{18}, 3));
    }

    public static int maximumTop(int[] nums, int k) {
        if (nums.length == 1) {
            return (k & 1) == 1 ? -1 : nums[0];
        }

        int max = -1;

        int end = Math.min(nums.length, k - 1);

        for (int i = 0; i < end; i++) {
            max = Math.max(max, nums[i]);
        }

        if (k < nums.length) {
            max = Math.max(max, nums[k]);
        }

        return max;
    }

}
