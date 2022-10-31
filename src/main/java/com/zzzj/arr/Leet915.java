package com.zzzj.arr;

public class Leet915 {

    public static int partitionDisjoint(int[] nums) {

        int N = nums.length;

        int[] min = new int[N];

        min[N - 1] = nums[N - 1];

        for (int i = N - 2; i >= 0; i--) {
            min[i] = Math.min(nums[i], min[i + 1]);
        }

        int max = nums[0];

        for (int i = 1; i < N; i++) {
            if (max <= min[i]) {
                return i;
            }
            max = Math.max(max, nums[i]);
        }

        return -1;
    }

}
