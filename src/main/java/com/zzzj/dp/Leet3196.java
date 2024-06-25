package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2024-06-25 11:22
 */
public class Leet3196 {

    public static void main(String[] args) {

        System.out.println(maximumTotalCost(new int[]{1, -2, 3, 4}));

        System.out.println(maximumTotalCost(new int[]{1, -1, 1, -1}));

    }

    public static long maximumTotalCost(int[] nums) {

        int N = nums.length;

        long[][] f = new long[N][2];

        f[0][1] = nums[0];
        f[0][0] = Integer.MIN_VALUE;

        for (int i = 1; i < N; i++) {
            f[i][0] = f[i - 1][1] - nums[i];
            f[i][1] = Math.max(
                    f[i - 1][0] + nums[i],
                    f[i - 1][1] + nums[i]
            );
        }

        return Math.max(
                f[N - 1][0],
                f[N - 1][1]
        );
    }

}
