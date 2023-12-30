package com.zzzj.contest.week371;

public class Q1 {

    public static void main(String[] args) {

        System.out.println(maximumStrongPairXor(new int[]{1, 2, 3, 4, 5}));

    }

    public static int maximumStrongPairXor(int[] nums) {

        int N = nums.length;

        int ans = 0;

        for (int i = 0; i < N; i++) {

            for (int j = i; j < N; j++) {

                if (Math.abs(nums[i] - nums[j]) <= Math.min(nums[i], nums[j]))
                    ans = Math.max(ans, nums[i] ^ nums[j]);
            }

        }

        return ans;
    }

}
