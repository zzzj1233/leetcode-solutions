package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-06-27 15:41
 */
public class Leet2304 {

    public static void main(String[] args) {

        System.out.println(minPathCost(
                LeetUtils.convertInts("[[5,3],[4,0],[2,1]]"),
                LeetUtils.convertInts("[[9,8],[1,5],[10,12],[18,6],[2,4],[14,3]]")
        ));

        System.out.println(minPathCost(
                LeetUtils.convertInts("[[5,1,2],[4,0,3]]"),
                LeetUtils.convertInts("[[12,10,15],[20,23,8],[21,7,1],[8,1,13],[9,10,25],[5,3,2]]")
        ));

    }

    public static int minPathCost(int[][] grid, int[][] moveCost) {

        int M = grid.length;

        int N = grid[0].length;

        int[][] dp = new int[M][N];

        for (int i = 0; i < N; i++) {
            dp[M - 1][i] = grid[M - 1][i];
        }

        for (int i = M - 2; i >= 0; i--) {

            for (int j = N - 1; j >= 0; j--) {

                int value = grid[i][j];

                int ret = Integer.MAX_VALUE;

                for (int k = 0; k < N; k++) {
                    ret = Math.min(ret, dp[i + 1][k] + moveCost[value][k] + value);
                }

                dp[i][j] = ret;
            }

        }

        return Arrays.stream(dp[0]).min().orElse(0);
    }

    public static int dfs(int[][] grid, int[][] moveCost, int x, int y) {

        if (x == grid.length - 1) return grid[x][y];

        int item = grid[x][y];

        int ret = Integer.MAX_VALUE;

        for (int i = 0; i < grid[x].length; i++) {

            int cost = moveCost[item][i];

            ret = Math.min(ret, dfs(grid, moveCost, x + 1, i) + cost + item);
        }


        return ret;
    }

}
