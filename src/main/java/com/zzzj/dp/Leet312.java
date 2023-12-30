package com.zzzj.dp;

public class Leet312 {

    public static void main(String[] args) {

        System.out.println(maxCoins(new int[]{3, 1, 5, 8}));

    }

    public static int maxCoins(int[] nums) {

        int N = nums.length;

        int[][] dp = new int[N + 1][N];

        for (int i = 0; i < N; i++)
            dp[i][i] = (i == 0 ? 1 : nums[i - 1]) * nums[i] * (i == N - 1 ? 1 : nums[i + 1]);

        for (int len = 1; len < N; len++) {

            for (int left = 0; left < N; left++) {

                int right = left + len;

                if (right >= N)
                    break;

                for (int k = left; k <= right; k++) {

                    dp[left][right] = Math.max(
                            dp[left][right],
                            (k - 1 >= 0 ? dp[left][k - 1] : 0) + dp[k + 1][right] + (
                                    nums[k] * (left - 1 < 0 ? 1 : nums[left - 1]) * (right + 1 >= N ? 1 : nums[right + 1])
                            )
                    );

                }

            }

        }

        return dp[0][N - 1];
    }

}
