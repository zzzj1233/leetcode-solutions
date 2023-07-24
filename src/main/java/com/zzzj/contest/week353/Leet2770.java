package com.zzzj.contest.week353;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-07-12 12:24
 */
public class Leet2770 {

    public static void main(String[] args) {

        System.out.println(maximumJumps(new int[]{1, 3, 6, 4, 1, 2}, 2));

        System.out.println(maximumJumps(new int[]{1, 3, 6, 4, 1, 2}, 3));

        System.out.println(maximumJumps(new int[]{1, 3, 6, 4, 1, 2}, 0));

    }

    public static int maximumJumps(int[] nums, int target) {

        int N = nums.length;

        // 1. next 必须 > i
        // 2. -target <= nums[next] - nums[i] <= target

        int[] dp = new int[N];

        Arrays.fill(dp, -1);

        dp[N - 1] = 0;

        for (int i = N - 1; i >= 0; i--) {

            if (dp[i] == -1) continue;

            int num = nums[i];

            // 我可以往左边哪跳?
            for (int j = 0; j < i; j++) {
                if (num - nums[j] <= target && num - nums[j] >= -target) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }

        }

        return dp[0];
    }

}
