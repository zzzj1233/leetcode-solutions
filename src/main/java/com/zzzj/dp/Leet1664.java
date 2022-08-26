package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-08-25 15:44
 */
public class Leet1664 {

    public static void main(String[] args) {
        System.out.println(waysToMakeFair(new int[]{1, 1}));
    }

    public static int waysToMakeFair(int[] nums) {

        int N = nums.length;

        if (N == 1) {
            return 1;
        }

        int[][] dp = new int[N][2];
        int[][] dp2 = new int[N][2];

        dp[0][1] = nums[0];

        for (int i = 1; i < N; i++) {
            if (i % 2 == 0) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + nums[i];
            } else {
                dp[i][0] = dp[i - 1][0] + nums[i];
                dp[i][1] = dp[i - 1][1];
            }
        }

        dp2[N - 1][N % 2 == 0 ? 0 : 1] = nums[N - 1];

        for (int i = N - 2; i >= 0; i--) {
            if (i % 2 == 0) {
                dp2[i][0] = dp2[i + 1][0];
                dp2[i][1] = dp2[i + 1][1] + nums[i];
            } else {
                dp2[i][0] = dp2[i + 1][0] + nums[i];
                dp2[i][1] = dp2[i + 1][1];
            }
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int leftOdd = i == 0 ? 0 : dp[i - 1][0];
            int leftEven = i == 0 ? 0 : dp[i - 1][1];

            int rightOdd = i == N - 1 ? 0 : dp2[i + 1][1];
            int rightEven = i == N - 1 ? 0 : dp2[i + 1][0];

            if (leftOdd + rightOdd == leftEven + rightEven) {
                ans++;
            }

        }

        return ans;
    }

}
