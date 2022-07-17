package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-07-09 17:42
 */
public class Leet403 {

    public static void main(String[] args) {
        System.out.println(canCross(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));
        System.out.println(canCross(new int[]{0, 1, 2, 3, 4, 8, 9, 11}));
    }

    public static boolean canCross(int[] stones) {
        if (stones[1] != 1) {
            return false;
        }
        return dp(stones);
    }


    public static boolean dp(int[] stones) {
        int N = stones.length;

        boolean[][] dp = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            dp[N - 1][i] = true;
        }

        for (int i = N - 2; i >= 1; i--) {

            for (int j = i - 1; j >= 0; j--) {

                int step = stones[i] - stones[j];

                int index1 = Arrays.binarySearch(stones, i + 1, stones.length, stones[i] + step + 1);

                int index2 = Arrays.binarySearch(stones, i + 1, stones.length, stones[i] + step);

                int index3 = Arrays.binarySearch(stones, i + 1, stones.length, stones[i] + step - 1);

                dp[i][j] = pick(dp, index1, i) || pick(dp, index2, i) || pick(dp, index3, i);
            }

        }

        return dp[1][0];
    }

    public static boolean pick(boolean[][] dp, int i, int j) {
        if (i < 0) {
            return false;
        }
        return dp[i][j];
    }

    public static boolean dfs(int[] stones, int i, int preIndex) {
        if (i == stones.length - 1) {
            return true;
        }

        if (i < 0) {
            return false;
        }

        int step = stones[i] - stones[preIndex];

        int index1 = Arrays.binarySearch(stones, i + 1, stones.length, stones[i] + step + 1);

        int index2 = Arrays.binarySearch(stones, i + 1, stones.length, stones[i] + step);

        int index3 = Arrays.binarySearch(stones, i + 1, stones.length, stones[i] + step - 1);

        return dfs(stones, index1, i) || dfs(stones, index2, i) || dfs(stones, index3, i);
    }


}
