package com.zzzj.matrix;

import com.zzzj.leet.LeetUtils;

public class Leet2658 {

    public static void main(String[] args) {

        System.out.println(findMaxFish(LeetUtils.convertInts("[[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]")));

    }

    public static int findMaxFish(int[][] grid) {

        int M = grid.length;

        int N = grid[0].length;

        int ans = 0;

        boolean[][] visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                if (grid[i][j] > 0)
                    ans = Math.max(ans, dfs(grid, i, j, visited));

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

    public static int dfs(int[][] grid, int x, int y, boolean[][] visited) {

        if (visited[x][y]) return 0;

        visited[x][y] = true;

        int fish = grid[x][y];

        for (int[] dir : DIRS) {

            int r = dir[0] + x;

            int c = dir[1] + y;

            if (r >= 0 && c >= 0 && r < visited.length && c < visited[r].length && grid[r][c] > 0)
                fish += dfs(grid, r, c, visited);
        }

        return fish;
    }

}
