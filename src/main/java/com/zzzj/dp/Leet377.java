package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-07-19 16:55
 */
public class Leet377 {

    public static void main(String[] args) {

        System.out.println(combinationSum4(new int[]{1, 2, 3}, 4));

        System.out.println(combinationSum4(new int[]{9}, 3));

    }

    public static int combinationSum4(int[] nums, int target) {
        int N = nums.length;

        int[] dp = new int[target + 1];

        dp[target] = 1;

        for (int i = target - 1; i >= 0; i--) {

            for (int j = 0; j < N; j++) {
                int num = nums[j];

                if (i + num <= target) {
                    dp[i] += dp[i + num];
                }
            }

        }

        return dp[0];
    }

    public static int dfs(int[] nums, int target, int sum) {
        if (sum == target) {
            return 1;
        }

        if (sum > target) {
            return 0;
        }

        int result = 0;

        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];

            result += dfs(nums, target, sum + num);
        }

        return result;
    }
}
