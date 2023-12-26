package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-24 11:14
 */
public class Leet1289 {

    public static void main(String[] args) {

        System.out.println(minFallingPathSum(
                LeetUtils.convertInts("[[1,2,3],[4,5,6],[7,8,9]]")
        ));

        System.out.println(minFallingPathSum(
                LeetUtils.convertInts("[[7]]")
        ));

    }

    public static int minFallingPathSum(int[][] grid) {

        int N = grid.length;

        int M = grid[0].length;

        int[][] f = new int[2][M];

        for (int i = 1; i <= N; i++) {

            int[] cur = f[i % 2];

            int[] prev = f[(i - 1) % 2];

            cur[0] = (1 >= M ? 0 : prev[1]) + grid[i - 1][0];

            for (int j = 1; j < M - 1; j++) {
                cur[j] = Math.min(
                        prev[j - 1],
                        prev[j + 1]
                ) + grid[i - 1][j];
            }

            cur[M - 1] = (M - 2 < 0 ? 0 : prev[M - 2]) + grid[i - 1][M - 1];
        }

        return Arrays.stream(f[N % 2]).min().orElse(0);
    }

}
