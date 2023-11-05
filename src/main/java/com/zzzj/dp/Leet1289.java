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

        int M = grid.length;

        if (M == 1)
            return Arrays.stream(grid[0]).min().orElse(0);

        int N = grid[0].length;

        int[] preRow = new int[N];
        int[] curRow = new int[N];

        Arrays.fill(curRow, Integer.MAX_VALUE);

        for (int i = 1; i <= M; i++) {

            int[] row = grid[i - 1];

            for (int j = 0; j < N; j++) {

                for (int k = 0; k < N; k++) {
                    if (j == k)
                        continue;
                    curRow[k] = Math.min(curRow[k], preRow[j] + row[k]);
                }

            }

            int[] temp = curRow;
            curRow = preRow;
            preRow = temp;
            Arrays.fill(curRow, Integer.MAX_VALUE);
        }

        return Arrays.stream(preRow).min().orElse(0);
    }

}
