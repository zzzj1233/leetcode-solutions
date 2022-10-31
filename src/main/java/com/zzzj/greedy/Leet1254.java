package com.zzzj.greedy;

/**
 * @author Zzzj
 * @create 2022-09-25 20:22
 */
public class Leet1254 {


    public static int closedIsland(int[][] grid) {
        int ans = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0 && dfs(grid, i, j)) {
                    ans++;
                }
            }
        }

        return ans;
    }

    public static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    public static boolean dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length) {
            return false;
        }

        if (grid[i][j] == 1) {
            return true;
        }

        grid[i][j] = 1;

        boolean result = true;

        for (int[] dir : DIRS) {
            int row = dir[0];
            int col = dir[1];
            if (!dfs(grid, i + row, j + col)) {
                result = false;
            }
        }

        return result;
    }


}
