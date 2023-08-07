package com.zzzj.contest.dweek103;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2023-08-02 16:29
 */
public class Leet2658 {

    public static void main(String[] args) {

        System.out.println(findMaxFish(LeetUtils.convertInts("[[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]")));

        System.out.println(findMaxFish(LeetUtils.convertInts("[[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]")));

    }

    private static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    public static int findMaxFish(int[][] grid) {
        int ans = 0;

        int M = grid.length;

        int N = grid[0].length;

        boolean[][] visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                ans = Math.max(ans, dfs(grid, visited, i, j));

            }

        }

        return ans;
    }

    public static int dfs(int[][] grid, boolean[][] visited, int x, int y) {

        if (visited[x][y] || grid[x][y] == 0) return 0;

        visited[x][y] = true;

        int result = grid[x][y];

        for (int[] dir : DIRS) {

            int row = x + dir[0];

            int col = y + dir[1];

            if (row >= 0 && col >= 0 && row < grid.length && col < grid[row].length) {
                result += dfs(grid, visited, row, col);
            }

        }

        return result;
    }

}
