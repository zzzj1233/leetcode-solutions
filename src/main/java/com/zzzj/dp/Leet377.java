package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-07-19 16:55
 */
public class Leet377 {

    public static void main(String[] args) {
        System.out.println(combinationSum4(new int[]{1, 2, 3}, 4));
    }

    public static int combinationSum4(int[] nums, int target) {
        return dp(nums, target);
    }

    public static int dp(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[target] = 1;

        for (int i = target - 1; i >= 0; i--) {

            for (int num : nums) {
                if (i + num <= target) {
                    dp[i] += dp[i + num];
                }
            }

        }

        return dp[0];
    }

    public static int dfs(int[] nums, int target, int cur) {
        if (cur == target) {
            return 1;
        }

        if (cur > target) {
            return 0;
        }

        int result = 0;

        for (int num : nums) {
            result += dfs(nums, target, cur + num);
        }

        return result;
    }

}
