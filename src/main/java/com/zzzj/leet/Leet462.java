package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-07-31 14:37
 */
public class Leet462 {


    public static void main(String[] args) {
        System.out.println(minMoves2(new int[]{1, 0, 0, 8, 6}));
    }

    public static int minMoves2(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        Arrays.sort(nums);

        // 找中位数

        int midValue;

        int N = nums.length;

        if (N % 2 == 0) {
            midValue = (nums[N / 2 - 1] + nums[N / 2]) / 2;
        } else {
            midValue = nums[N / 2];
        }

        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            ans += Math.abs(midValue - nums[i]);
        }

        return ans;
    }

}
