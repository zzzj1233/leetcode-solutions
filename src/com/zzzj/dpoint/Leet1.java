package com.zzzj.dpoint;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2021-12-20 21:29
 */
public class Leet1 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
    }

    public static int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);

        int j = nums.length - 1;

        for (int i = 0; i < j; ) {

            int sum = nums[i] + nums[j];

            if (sum == target) {
                return new int[]{i, j};
            } else if (sum > target) {
                // j ++ 或者 i--
                j--;
            } else {
                i++;
            }
        }

        return new int[]{-1, -1};
    }

}
