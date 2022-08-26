package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-08-25 18:38
 */
public class Leet1262 {

    public static void main(String[] args) {
        System.out.println(maxSumDivThree(new int[]{3, 6, 5, 1, 8}));
        System.out.println(maxSumDivThree(new int[]{1, 2, 3, 4, 4}));
    }

    public static int maxSumDivThree(int[] nums) {
        int N = nums.length;

        int[] dp = new int[3];

        Arrays.fill(dp, Integer.MIN_VALUE);

        dp[0] = 0;

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int num = nums[i];

            int mod = num % 3;

            int dp0 = dp[0];
            int dp1 = dp[1];
            int dp2 = dp[2];

            if (mod == 0) {
                dp[0] = dp0 + num;
                dp[1] = dp1 + num;
                dp[2] = dp2 + num;
            } else if (mod == 1) {
                dp[0] = Math.max(dp0, dp2 + num);
                dp[2] = Math.max(dp2, dp1 + num);
                dp[1] = Math.max(dp1, dp0 + num);
            } else {
                dp[0] = Math.max(dp0, dp1 + num);
                dp[1] = Math.max(dp1, dp2 + num);
                dp[2] = Math.max(dp2, dp0 + num);
            }

            ans = Math.max(ans, dp[0]);
        }

        return ans;
    }

}
