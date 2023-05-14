package com.zzzj.greedy;

import com.zzzj.graph.leetcode.FarmerAndSheep;

import java.util.Arrays;

public class Leet2567 {

    public static void main(String[] args) {

        System.out.println(minimizeSum(new int[]{1, 4, 3}));

        System.out.println(minimizeSum(new int[]{1, 4, 7, 8, 5}));

    }

    public static int minimizeSum(int[] nums) {

        int length = nums.length;

        if (length <= 3) {
            return 0;
        }

        Arrays.sort(nums);

        return Math.min(
                Math.abs(nums[length - 1] - nums[2]),
                Math.min(
                        Math.abs(nums[length - 3] - nums[0]),
                        Math.abs(nums[length - 2] - nums[1])
                )
        );
    }

}
