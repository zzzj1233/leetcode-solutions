package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-11-04 18:18
 */
public class Leet55 {


    public static void main(String[] args) {
        System.out.println(canJump(new int[]{0, 1}));
    }

    public static boolean canJump(int[] nums) {
        if (nums.length < 2) {
            return true;
        }
        return dfs(nums, 0) || dfs(nums, 1);
    }

    private static boolean dfs(int[] nums, int i) {
        if (i >= nums.length) {
            return false;
        }

        if (nums[i] == 0) {
            return false;
        }

        if (nums[i] + i >= nums.length - 1) {
            return true;
        }

        return dfs(nums, i + 1);
    }

}
