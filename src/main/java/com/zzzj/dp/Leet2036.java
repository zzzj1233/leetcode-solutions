package com.zzzj.dp;

public class Leet2036 {

    public static void main(String[] args) {
        System.out.println(maximumAlternatingSubarraySum(new int[]{-7, -7, -7, -7}));
    }

    public static long maximumAlternatingSubarraySum(int[] nums) {
        int N = nums.length;

        if (N == 1) {
            return nums[0];
        }

        long[][] dp = new long[N + 1][2];

        dp[0][0] = nums[0];
        dp[0][1] = Integer.MIN_VALUE;

        dp[1][0] = nums[1];
        dp[1][1] = nums[0] - nums[1];

        long ans = (int) Math.max(Math.max(0, dp[0][0]), Math.max(dp[1][0], dp[1][1]));

        for (int i = 2; i < N; i++) {
            int num = nums[i];

            dp[i][0] = Math.max(num, dp[i - 1][1] + num);
            dp[i][1] = dp[i - 1][0] - num;

            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }

        return ans;
    }


}
