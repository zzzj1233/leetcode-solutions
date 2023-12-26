package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2023-11-06 15:55
 */
public class Leet1463 {

    public static void main(String[] args) {

        System.out.println(cherryPickup(LeetUtils.convertInts("[[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]")));

        System.out.println(cherryPickup(LeetUtils.convertInts("[[1,0,0,3],[0,0,0,3],[0,0,3,3],[9,0,3,3]]")));

        System.out.println(cherryPickup(LeetUtils.convertInts("[[0,8,7,10,9,10,0,9,6],[8,7,10,8,7,4,9,6,10],[8,1,1,5,1,5,5,1,2],[9,4,10,8,8,1,9,5,0],[4,3,6,10,9,2,4,8,10],[7,3,2,8,3,3,5,9,8],[1,2,6,5,6,2,0,10,0]]")));

    }

    public static int cherryPickup(int[][] grid) {

        int M = grid.length;

        int N = grid[0].length;

        int[][][] dp = new int[M][N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[0][i][j] = Integer.MIN_VALUE;
            }
        }

        dp[0][0][N - 1] = grid[0][0] + grid[0][N - 1];

        for (int i = 1; i < M; i++) {

            int[][] prev = dp[i - 1];

            for (int x = 0; x < N; x++) {

                for (int y = 0; y < N; y++) {

                    int max = max(
                            pick(prev, x - 1, y - 1),
                            pick(prev, x - 1, y),
                            pick(prev, x - 1, y + 1),
                            pick(prev, x, y - 1),
                            pick(prev, x, y),
                            pick(prev, x, y + 1),
                            pick(prev, x + 1, y - 1),
                            pick(prev, x + 1, y),
                            pick(prev, x + 1, y + 1)
                    );


                    dp[i][x][y] = max + (x == y ? grid[i][x] : grid[i][x] + grid[i][y]);
                }

            }

        }

        int ans = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, dp[M - 1][i][j]);
            }
        }

        return ans;
    }

    private static int max(int... values) {
        int max = Integer.MIN_VALUE;
        for (int value : values) {
            max = Math.max(max, value);
        }
        return max;
    }

    private static int pick(int[][] dp, int x, int y) {
        if (x < 0 || y < 0 || x >= dp.length || y >= dp.length)
            return Integer.MIN_VALUE;
        return dp[x][y];
    }

}
