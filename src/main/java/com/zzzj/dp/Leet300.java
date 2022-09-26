package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-11-01 11:48
 */
public class Leet300 {

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    // [10,9,2,5,3,7,101,18]
    public static int lengthOfLIS(int[] nums) {

        int N = nums.length;

        int[] dp = new int[N];

        int ans = 0;

        for (int i = 0; i < N; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1 );
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static int dfs(int[] nums, int maxIndex, int index) {
        if (index >= nums.length) {
            return 0;
        }

        int ways1 = 0;

        if (nums[index] > nums[maxIndex]) {
            ways1 = 1 + dfs(nums, index, index + 1);
        }

        int ways2 = dfs(nums, index, index + 1);

        return Math.max(ways1, ways2);
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
