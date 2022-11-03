package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-11-03 14:44
 */
public class Leet1509 {

    public static int minDifference(int[] nums) {

        int N = nums.length;

        if (N <= 3) {
            return 0;
        }

        Arrays.sort(nums);

        int ans = Integer.MAX_VALUE;

        ans = Math.min(ans, nums[N - 1] - nums[3]);
        ans = Math.min(ans, nums[N - 2] - nums[2]);
        ans = Math.min(ans, nums[N - 3] - nums[1]);
        ans = Math.min(ans, nums[N - 4] - nums[0]);

        return ans;
    }

}
