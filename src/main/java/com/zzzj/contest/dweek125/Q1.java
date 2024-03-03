package com.zzzj.contest.dweek125;

public class Q1 {

    public static int minOperations(int[] nums, int k) {

        int ans = 0;

        for (int num : nums) {
            if (num < k)
                ans++;
        }

        return ans;
    }

}
