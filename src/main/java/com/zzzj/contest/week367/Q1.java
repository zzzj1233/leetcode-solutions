package com.zzzj.contest.week367;

public class Q1 {

    public static void main(String[] args) {

    }

    public static int[] findIndices(int[] nums, int indexDifference, int valueDifference) {

        int N = nums.length;

        for (int i = 0; i < N; i++) {

            for (int j = i + indexDifference; j < N; j++) {

                if (Math.abs(nums[i] - nums[j]) >= valueDifference)
                    return new int[]{i, j};

            }

        }

        return new int[]{-1, -1};
    }


}
