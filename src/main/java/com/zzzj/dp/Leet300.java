package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-11-01 11:48
 */
public class Leet300 {

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{1, 3, 5, 4, 7}));
    }

    // [10,9,2,5,3,7,101,18]
    public static int lengthOfLIS(int[] nums) {
        int N = nums.length;

        int[] dp = new int[N];

        int ans = 0;

        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static int dfs(int[] nums, int index) {
        int result = 1;
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] > nums[index]) {
                result = Math.max(result, 1 + dfs(nums, i));
            }
        }
        return result;
    }

    public static int[] dp(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        dp[N - 1] = 1;
        for (int i = N - 2; i >= 0; i--) {
            int result = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    result = Math.max(result, 1 + dp[j]);
                }
            }
            dp[i] = result;
        }

        return dp;
    }


}
