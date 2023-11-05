package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-05-19 16:40
 */
public class Leet1335 {

    public static void main(String[] args) {

        System.out.println(minDifficulty(new int[]{6, 5, 4, 3, 2, 1}, 2));
//
        System.out.println(minDifficulty(new int[]{7, 1, 7, 1, 7, 1}, 3));

        System.out.println(minDifficulty(new int[]{1, 1, 1}, 3));
//
        System.out.println(minDifficulty(new int[]{11, 111, 22, 222, 33, 333, 44, 444}, 6));

    }

    public static int minDifficulty(int[] jobDifficulty, int d) {

        int N = jobDifficulty.length;

        if (d > N)
            return -1;

        int[][] dp = new int[d][N];

        dp[0][0] = jobDifficulty[0];

        for (int i = 1; i < N - d + 1; i++) {
            dp[0][i] = Math.max(
                    dp[0][i - 1],
                    jobDifficulty[i]
            );
        }

        for (int i = 1; i < d; i++) {

            int right = N - (d - i);

            Arrays.fill(dp[i], Integer.MAX_VALUE);

            for (int j = right; j >= i; j--) {

                int max = 0;

                for (int k = j; k >= i; k--) {

                    max = Math.max(max, jobDifficulty[k]);

                    dp[i][j] = Math.min(
                            dp[i][j],
                            dp[i - 1][k - 1] + max
                    );

                }


            }

        }

        return dp[d - 1][N - 1];
    }

}
