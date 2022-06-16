package com.zzzj.dp;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-16 14:48
 */
public class Leet486 {

    public static void main(String[] args) {

//        System.out.println(PredictTheWinner(new int[]{3, 3, 2, 5, 2}));
//        System.out.println(PredictTheWinner(new int[]{3, 3, 2, 5, 2}));
//        System.out.println(PredictTheWinner(new int[]{1, 5, 2}));
//        System.out.println(PredictTheWinner(new int[]{1, 5, 233, 7}));

//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            final int[] arr = ArrayUtil.generateArray(10, 0, 100);
            if (PredictTheWinner(arr) != right(arr)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                return;
            }
        }

        System.out.println("Ok");
    }

    public static boolean PredictTheWinner(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int res = dp(nums);
        return sum - res <= res;
    }

    public static int dp(int[] nums) {
        int N = nums.length;

        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = N - 2; i >= 0; i--) {

            for (int j = i + 1; j < N; j++) {
                int sum1 = nums[i] + Math.min(pick(dp, i + 2, j), pick(dp, i + 1, j - 1));
                int sum2 = nums[j] + Math.min(pick(dp, i, j - 2), pick(dp, i + 1, j - 1));
                dp[i][j] = Math.max(sum1, sum2);
            }

        }

        return dp[0][N - 1];
    }

    public static int pick(int[][] dp, int i, int j) {
        if (i < 0 || j < 0 || i >= dp.length || j >= dp[i].length) {
            return 0;
        }
        return dp[i][j];
    }

    public static int dfs(int[] nums, int i, int j) {
        if (i == j) {
            return nums[i];
        }
        if (i > j) {
            return 0;
        }
        // 可以拿i的,也可以拿j的
        int sum1 = nums[i] + Math.min(dfs(nums, i + 2, j), dfs(nums, i + 1, j - 1));
        int sum2 = nums[j] + Math.min(dfs(nums, i, j - 2), dfs(nums, i + 1, j - 1));
        return Math.max(sum1, sum2);
    }


    public static boolean right(int[] nums) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++)
            dp[i][i] = nums[i];
        for (int j = 1; j < len; j++)
            dp[j - 1][j] = Math.max(dp[j - 1][j - 1], dp[j][j]);
        // 按照对角线来递推
        for (int i = 2; i < len; i++)
            for (int row = 0; i + row < len; row++)
                dp[row][row + i] = Math.max(nums[row] + Math.min(dp[row + 1][i + row - 1], dp[row + 2][i + row]),
                        nums[i + row] + Math.min(dp[row][i + row - 2], dp[row + 1][i + row - 1]));
        return dp[0][len - 1] >= (sum - dp[0][len - 1]);
    }

}
