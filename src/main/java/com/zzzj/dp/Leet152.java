package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-11-04 15:38
 */
public class Leet152 {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{-2, 4, 4, -8}));
    }

    // [-2,3,-4]
    public static int maxProduct(int[] nums) {
        int N = nums.length;

        int[][] dp = new int[N][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        int ans = nums[0];

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(nums[i], Math.max(nums[i] * dp[i - 1][0], nums[i] * dp[i - 1][1]));

            dp[i][1] = Math.min(nums[i], Math.min(nums[i] * dp[i - 1][0], nums[i] * dp[i - 1][1]));

            ans = Math.max(ans, dp[i][0]);
        }

        return ans;
    }

}
