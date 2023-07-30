package com.zzzj.contest.week351;


/**
 * @author zzzj
 * @create 2023-07-24 12:37
 */
public class Leet2750 {

    public static void main(String[] args) {
//
        System.out.println(numberOfGoodSubarraySplits(new int[]{0, 1, 0, 0, 1}));

        System.out.println(numberOfGoodSubarraySplits(new int[]{0, 1, 0}));

        System.out.println(numberOfGoodSubarraySplits(new int[]{1, 0, 0, 0, 0, 0, 1, 0, 1}));

        System.out.println(dfs(new int[]{1, 0, 0, 0, 0, 0, 1, 0, 1}, 0, false));


        System.out.println(numberOfGoodSubarraySplits(
                new int[]{0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0}
        ));
    }


    public static int numberOfGoodSubarraySplits(int[] nums) {

        int N = nums.length;

        long[][] dp = new long[N][2];

        final int ZERO_IDX = 0;

        final int ONE_IDX = 1;

        dp[N - 1][ZERO_IDX] = nums[N - 1];
        dp[N - 1][ONE_IDX] = nums[N - 1] == 0 ? 1 : 0;

        final int MOD = 1000000007;

        for (int i = N - 2; i >= 0; i--) {

            int num = nums[i];

            if (num == 1) {
                dp[i][ONE_IDX] = 0;
                dp[i][ZERO_IDX] = dp[i + 1][ZERO_IDX] + dp[i + 1][ONE_IDX];
                dp[i][ZERO_IDX] %= MOD;
            } else {
                dp[i][ONE_IDX] = dp[i + 1][ZERO_IDX] + dp[i + 1][ONE_IDX];
                dp[i][ZERO_IDX] = dp[i + 1][ZERO_IDX];
                dp[i][ONE_IDX] %= MOD;
                dp[i][ZERO_IDX] %= MOD;
            }
        }

        return (int) (dp[0][0] % 1000000007);
    }

    public static int dfs(int[] nums, int index, boolean one) {

        if (index == nums.length - 1) {
            if (one) return nums[index] == 0 ? 1 : 0;
            return nums[index];
        }

        if (nums[index] == 1) {
            if (one) return 0;
            return dfs(nums, index + 1, true) + dfs(nums, index + 1, false);
        } else {
            if (one) return dfs(nums, index + 1, true) + dfs(nums, index + 1, false);
            return dfs(nums, index + 1, false);
        }
    }

}
