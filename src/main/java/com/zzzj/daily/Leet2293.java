package com.zzzj.daily;

public class Leet2293 {

    public static int minMaxGame(int[] nums) {

        int N = nums.length;

        while (N > 1) {
            int mid = N / 2;
            for (int i = 0; i < mid; i++) {
                if (i % 2 == 0) {
                    nums[i] = Math.min(nums[2 * i], nums[2 * i + 1]);
                } else {
                    nums[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
                }
            }
            N = mid;
        }

        return nums[0];
    }

}
