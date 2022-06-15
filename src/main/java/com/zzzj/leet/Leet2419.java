package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-06-07 21:23
 */
public class Leet2419 {

    public static int[] twoSum(int[] nums, int target) {

        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum < target) {
                l++;
            } else if (sum > target) {
                r--;
            } else {
                return new int[]{nums[l], nums[r]};
            }
        }

        return null;
    }

}
