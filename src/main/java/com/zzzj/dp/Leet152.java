package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-11-04 15:38
 */
public class Leet152 {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));
        // System.out.println(dynamicPlanning(new int[]{}));
    }

    private static int dynamicPlanning(int[] nums) {
        int n = nums.length;

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return nums[0];
        }

        if (n == 2) {
            return Math.max(nums[0], Math.max(nums[1], nums[0] * nums[1]));
        }

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            dp[n][i] = 1;
            dp[i][n] = 1;
        }

        for (int i = n - 2; i >= 0; i--) {

            for (int j = i - 1; j >= 0; j--) {
                int val1 = nums[i] * nums[j];
                int val2 = dp[i + 1][j + 1];
                int val3 = val1 * dp[i + 1][j + 1];
                dp[i][j] = Math.max(val1, Math.max(val2, val3));
            }

        }

        return dp[0][1];
    }

    public static int maxProduct(int[] nums) {
        return dfs(nums, 0, 1);
    }

    private static int dfs(int[] nums, int i, int j) {
        if (i >= nums.length) {
            return 1;
        }

        int val1 = nums[i] * nums[j];

        int val2 = j + 1 >= nums.length ? Math.max(nums[i + 1], 0) : dfs(nums, i + 1, j + 1);

        int val3 = nums[i] * nums[j];

        if (i + 2 < nums.length) {
            if (j + 2 < nums.length) {
                val3 *= dfs(nums, i + 2, j + 2);
            } else {
                val3 *= nums[i + 2];
            }
        }


        return Math.max(val1, Math.max(val2, val3));
    }

}
