package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-07-22 21:49
 */
public class Leet695 {

    public static int maxAreaOfIsland(int[][] grid) {

        int N = grid.length;

        int M = grid[0].length;

        boolean[][] visited = new boolean[N][M];

        int ans = 0;

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++) {

                if (grid[i][j] == 1 && !visited[i][j]) {

                    ans = Math.max(ans, dfs(grid, i, j, visited));

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

    public static int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (visited[i][j]) {
            return 0;
        }

        visited[i][j] = true;

        int result = 1;

        for (int[] dir : DIRS) {
            int row = dir[0];
            int col = dir[1];
            if (i + row >= 0 && i + row < grid.length && j + col >= 0 && j + col < grid[i].length && !visited[i + row][j + col] && grid[i + row][j + col] == 1) {
                result += dfs(grid, i + row, j + col, visited);
            }
        }

        return result;
    }

}
