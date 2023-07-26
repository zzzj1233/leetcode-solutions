package com.zzzj.contest.week355;

public class Leet6951 {

    public static void main(String[] args) {

        System.out.println(maxArrayValue(new int[]{2, 3, 7, 9, 3}));

        System.out.println(maxArrayValue(new int[]{5, 3, 3}));

    }

    public static long maxArrayValue(int[] nums) {

        int N = nums.length;

        if (N == 1) return nums[0];

        long cur = nums[N - 1];

        long ans = cur;

        for (int i = N - 2; i >= 0; i--) {

            if (cur >= nums[i]) cur += nums[i];
            else cur = nums[i];

            ans = Math.max(ans, cur);
        }

        return ans;
    }

}
