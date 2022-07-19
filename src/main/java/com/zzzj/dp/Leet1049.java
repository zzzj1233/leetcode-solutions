package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-17 15:43
 */
public class Leet1049 {

    public static void main(String[] args) {
        // 1 1 2 4 7 8
        System.out.println(lastStoneWeightII(new int[]{1, 1, 2, 4, 7, 8}));
        System.out.println(lastStoneWeightII(new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 14, 23, 37, 61, 98}));

        // [53,54,3,61,67]
        System.out.println(lastStoneWeightII(new int[]{53, 54, 3, 61, 67}));
    }

    // 从stones中取一些石头，使得这些石头的总重量尽可能达到所有石头总重量sum的一半。这样就可以转化为0-1背包问题，背包最多能装下sum/2
    public static int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();

        int N = stones.length;

        int M = sum / 2 + 1;

        boolean[][] dp = new boolean[N][M];

        dp[0][stones[0]] = true;

        for (int i = 1; i < N; i++) {
            int stone = stones[i];

            for (int j = 0; j < M; j++) {
                if (dp[i - 1][j] && stone + j < M) {
                    dp[i][stone + j] = true;
                }
            }

            for (int j = 0; j < M; j++) {
                if (dp[i - 1][j]) {
                    dp[i][j] = true;
                }
            }
        }

        int max = 0;

        for (int i = M - 1; i >= 0; i--) {
            if (dp[N - 1][i]) {
                max = i;
                break;
            }
        }

        return sum - (max << 1);
    }

}
