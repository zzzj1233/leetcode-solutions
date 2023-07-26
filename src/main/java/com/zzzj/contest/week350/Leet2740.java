package com.zzzj.contest.week350;

import java.util.Arrays;

public class Leet2740 {

    public static void main(String[] args) {

        System.out.println(findValueOfPartition(new int[]{1, 3, 2, 4}));

        System.out.println(findValueOfPartition(new int[]{100, 1, 10}));

    }

    public static int findValueOfPartition(int[] nums) {

        Arrays.sort(nums);

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < nums.length; i++) {
            ans = Math.min(
                    ans,
                    nums[i] - nums[i - 1]
            );
        }

        return ans;
    }

}
