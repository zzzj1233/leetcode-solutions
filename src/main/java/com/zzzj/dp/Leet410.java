package com.zzzj.dp;


/**
 * @author Zzzj
 * @create 2022-07-12 08:38
 */
public class Leet410 {

    public static void main(String[] args) {
        System.out.println(splitArray(new int[]{7, 2, 5, 1}, 2));
    }

    public static int splitArray(int[] nums, int m) {

        int N = nums.length;

        int[][] dp = new int[N][m + 1];

        int[] preSum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < N; i++) {
            dp[i][1] = preSum[i + 1] - preSum[0];
        }

        for (int i = 1; i < N; i++) {

            int min = Integer.MAX_VALUE;

            for (int j = 2; j <= m; j++) {

                for (int k = 0; k < i; k++) {
                    min = Math.min(Math.max(dp[k][j - 1], preSum[i + 1] - preSum[k + 1]), min);
                }

                dp[i][j] = min;
            }


        }

        return dp[N - 1][m];
    }

}
