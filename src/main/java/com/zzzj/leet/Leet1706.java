package com.zzzj.leet;

import java.util.Arrays;

public class Leet1706 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(findBall(
                LeetUtils.convertInts("[[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]")
        )));
    }

    public static int[] findBall(int[][] grid) {

        int M = grid.length;
        int N = grid[0].length;

        // 有N个球

        int[] ans = new int[N];

        // 第i个球
        for (int i = 0; i < N; i++) {

            // 当前所在的列
            int col = i;


            for (int row = 0; row < N; row++) {

                int it = grid[row][col];

                // 往右边
                if (it == 1) {
                    // 堵死了
                    if (col == N - 1 || grid[row][col + 1] == -1) {
                        col = -1;
                        break;
                    }
                    col++;
                } else { // 往左边
                    if (col == 0 || grid[row][col - 1] == 1) {
                        col = -1;
                        break;
                    }
                    col--;
                }
            }

            ans[i] = col;
        }

        return ans;
    }

}
