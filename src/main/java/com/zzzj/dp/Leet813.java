package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-06-17 18:04
 */
public class Leet813 {

    public static void main(String[] args) {
//        System.out.println(largestSumOfAverages(new int[]{9, 1, 2, 3, 9}, 3));
//        System.out.println(largestSumOfAverages(new int[]{4, 1, 7, 5, 6, 2, 3}, 4));
        System.out.println(largestSumOfAverages(new int[]{4, 1, 7, 5, 6, 2, 3}, 4));
        System.out.println(dfs(new int[]{4, 1, 7, 5, 6, 2, 3}, 4, 0, 0));
    }

    public static double largestSumOfAverages(int[] nums, int k) {
//        return dp(nums, k);
        return dfs(nums, k, 0, 0);
    }

    public static double dp(int[] nums, int k) {
        int N = nums.length;

        double[][] dp = new double[N][k];

        for (int i = N - 1; i >= 0; i--) {

            for (int j = k - 1; j >= 0; j--) {

                double ways1 = nums[i] + pick(dp, i + 1, j + 1);

                double sum = nums[i];

                double ways2 = 0;

                for (int l = i + 1; l < N; l++) {
                    sum += nums[l];
                    ways2 = Math.max(ways2, (sum / (l - i + 1) + pick(dp, l + 1, j + 1)));
                }

                dp[i][j] = Math.max(ways1, ways2);
            }

        }
        return dp[0][0];
    }

    public static double pick(double[][] dp, int i, int j) {
        if (i >= dp.length || j >= dp[0].length) {
            return 0;
        }
        return dp[i][j];
    }

    // 最多k个分组
    public static double dfs(int[] nums, int k, int i, int group) {
        if (i >= nums.length) {
            return 0;
        }

        if (i == nums.length - 1) {
            return nums[i];
        }

        double result = 0;

        double sum = 0;

        if (group == k - 1) {
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
            }
            result = sum / (nums.length - i + 1);
        } else {
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                result = Math.max(result, (sum / (j - i + 1) + dfs(nums, k, j + 1, group + 1)));
            }
        }

        return result;
    }

}
