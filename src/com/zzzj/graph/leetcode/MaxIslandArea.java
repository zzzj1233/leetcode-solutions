package com.zzzj.graph.leetcode;

/**
 * @author Zzzj
 * @create 2021-05-02 14:56
 */

// 最大岛屿面积
public class MaxIslandArea {

    private int r;
    private int c;

    private boolean[][] visited;
    private int[][] grid;

    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;

        this.r = grid.length;
        this.c = grid[0].length;

        this.visited = new boolean[r][c];

        int maxIsland = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = area(i, j);
                    if (area > maxIsland) {
                        maxIsland = area;
                    }
                }
            }
        }

        return maxIsland;
    }

    private int area(int i, int j) {
        visited[i][j] = true;
        int total = 1;
        // 上下左右
        if ((i - 1) >= 0 && grid[i - 1][j] == 1 && !visited[i - 1][j]) {
            total += area(i - 1, j);
        }
        if ((i + 1) < r && grid[i + 1][j] == 1 && !visited[i + 1][j]) {
            total += area(i + 1, j);
        }
        if ((j - 1) >= 0 && grid[i][j - 1] == 1 && !visited[i][j - 1]) {
            total += area(i, j - 1);
        }
        if ((j + 1) < c && grid[i][j + 1] == 1 && !visited[i][j + 1]) {
            total += area(i, j + 1);
        }
        return total;
    }

}
