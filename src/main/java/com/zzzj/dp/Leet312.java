package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-12-12 12:47
 */
public class Leet312 {

    public static void main(String[] args) {

        System.out.println(maxCoins(new int[]{3, 1, 5, 8}));

        System.out.println(maxCoins(new int[]{1, 5}));

    }

    public static int maxCoins(int[] nums) {

        int N = nums.length;

        int[][] f = new int[N + 1][N];

        for (int len = 0; len < N; len++) {

            for (int left = 0; left < N; left++) {

                int right = left + len;

                if (right >= N)
                    break;

                for (int k = left; k <= right; k++) {

                    f[left][right] = Math.max(
                            f[left][right],
                            (left - 1 < 0 ? 1 : nums[left - 1]) * (right + 1 >= N ? 1 : nums[right + 1]) * nums[k]
                                    + (k - 1 < 0 ? 0 : f[left][k - 1]) + f[k + 1][right]
                    );

                }
            }

        }

        return f[0][N - 1];
    }

}
