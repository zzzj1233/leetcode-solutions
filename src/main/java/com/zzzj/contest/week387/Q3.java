package com.zzzj.contest.week387;

import com.zzzj.leet.LeetUtils;

public class Q3 {

    public static int minimumOperationsToWriteY(int[][] grid) {

        // 1. 属于 Y 的所有单元格的值相等
        // 2. 不属于 Y 的所有单元格的值相等
        // 3. 属于 Y 的单元格的值与不属于Y的单元格的值不同

        int res = Integer.MAX_VALUE;

        int N = grid.length;

        int M = grid[0].length;

        int left = 0;
        int right = M - 1;
        int row = 0;

        int[] y = new int[3];

        while (left != right) {
            y[grid[row][left]]++;
            y[grid[row][right]]++;
            left++;
            right--;
            row++;
        }

        while (row < N) {
            y[grid[row][left]]++;
            row++;
        }

        int[] tot = new int[3];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tot[grid[i][j]]++;
            }
        }

        tot[0] -= y[0];
        tot[1] -= y[1];
        tot[2] -= y[2];

//        System.out.println("y = " + Arrays.toString(y));
//        System.out.println("tot = " + Arrays.toString(tot));

        res = Math.min(res, check(0, 1, y, tot));
        res = Math.min(res, check(0, 2, y, tot));
        res = Math.min(res, check(1, 0, y, tot));
        res = Math.min(res, check(1, 2, y, tot));
        res = Math.min(res, check(2, 0, y, tot));
        res = Math.min(res, check(2, 1, y, tot));

        return res;
    }

    public static int check(int yn, int on, int[] y, int[] tot) {

        int ys = y[0] + y[1] + y[2] - y[yn];

        int ts = tot[0] + tot[1] + tot[2]  - tot[on];

        return ts + ys;
    }

    public static void main(String[] args) {

        System.out.println(minimumOperationsToWriteY(LeetUtils.convertInts("[[0,1,0,1,0],[2,1,0,1,2],[2,2,2,0,1],[2,2,2,2,2],[2,1,2,2,2]]")));

        System.out.println(minimumOperationsToWriteY(LeetUtils.convertInts("[[1,2,2],[1,1,0],[0,1,0]]")));

    }

}

