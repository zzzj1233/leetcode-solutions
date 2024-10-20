package com.zzzj.contest.week415;


import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-09-18 23:26
 */
public class Leet3290 {

    public static void main(String[] args) {

        System.out.println(maxScore(
                new int[]{3, 2, 5, 6},
                new int[]{2, -6, 4, -5, -3, 2, -7}
        ));

        System.out.println(maxScore(
                new int[]{-1, 4, 5, -2},
                new int[]{-5, -1, -3, -2, -4}
        ));

    }

    public static long maxScore(int[] a, int[] b) {

        // 1. a.length == 4
        // 2. b.length >= 4

        // 3. i0 < i1 < i2 < i3
        // 4. 最大化: a[0] * b[i0] + a[1] * b[i1] + a[2] * b[i2] + a[3] * b[i3]

        // 由于索引限制, 那么a和b不能排序

        int N = b.length;

        // 状态定义:
        // f[4][N] 表示f[i][N]的最大值
        // f[0][2] = 只考虑a[0] - b[0 - 2]的最大值

        // 状态转移方程
        // f[ai][bi] = f[ai - 1][ ? ] + max(a[ai] * b[? + 1 - bi]) require: ? < bi

        // 1. 先求出f0的所有值, end(bi) = N - 3 - 1

        long[][] f = new long[4][N];

        for (int i = 0; i < 4; i++)
            Arrays.fill(f[i], Long.MIN_VALUE);

        // base case
        f[0][0] = (long) a[0] * b[0];

        for (int i = 1; i <= N - 4; i++) {
            f[0][i] = Math.max(
                    Math.max(
                            f[0][i], f[0][i - 1]
                    ),
                    (long) a[0] * b[i]
            );
        }

        for (int i = 1; i <= N - 3; i++) {
            f[1][i] = Math.max(
                    Math.max(
                            f[1][i], f[1][i - 1]
                    ),
                    f[0][i - 1] + (long) a[1] * b[i]
            );
        }

        for (int i = 2; i <= N - 2; i++) {
            f[2][i] = Math.max(
                    Math.max(
                            f[2][i], f[2][i - 1]
                    ),
                    f[1][i - 1] + (long) a[2] * b[i]
            );
        }

        for (int i = 3; i <= N - 1; i++) {
            f[3][i] = Math.max(
                    Math.max(
                            f[3][i], f[3][i - 1]
                    ),
                    f[2][i - 1] + (long) a[3] * b[i]
            );
        }

        return f[3][N - 1];
    }


}
