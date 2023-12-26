package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-05-22 14:23
 */
public class Leet494 {

    public static void main(String[] args) {

        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));

         System.out.println(findTargetSumWays(new int[]{43, 1, 49, 22, 41, 1, 11, 1, 24, 10, 26, 49, 33, 4, 20, 19, 44, 42, 2, 37}, 17));

    }

    public static int findTargetSumWays(int[] nums, int target) {

        int N = nums.length;

        int s = Arrays.stream(nums).sum();

        if (target > s)
            return 0;

        if (target + s < 0)
            return 0;

        int M = (s << 1) + 1;

        int[][] f = new int[N + 1][M];

        f[0][s] = 1;

        for (int i = 1; i <= N; i++) {

            int w = nums[i - 1];

            for (int j = w; j < M; j++)
                f[i][j] += f[i - 1][j - w];

            for (int j = M - w - 1; j >= 0; j--) {
                f[i][j] += f[i - 1][j + w];
            }

        }

        return f[N][target + s];
    }

}
