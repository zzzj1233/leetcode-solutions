package com.zzzj.review;

/**
 * @author zzzj
 * @create 2023-03-10 19:17
 */
public class Leet53 {

    public static void main(String[] args) {

        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));

        System.out.println(maxSubArray(new int[]{5, 4, -1, 7, 8}));

    }

    public static int maxSubArray(int[] nums) {

        int N = nums.length;

        int[] dp = new int[N];

        dp[0] = nums[0];

        int ans = dp[0];

        for (int i = 1; i < N; i++) {

            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);

            ans = Math.max(ans, dp[i]);

        }

        return ans;
    }

}