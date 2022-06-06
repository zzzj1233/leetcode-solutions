package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-12-07 17:59
 */
public class Leet1034 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(colorBorder(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, 1, 1, 2)));
    }

    private static boolean[][] visited;

    public static int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length;
        int n = grid[0].length;

        visited = new boolean[m][n];

        int originColor = grid[row][col];

        dfs(grid, row, col, color, originColor);

        grid[row][col] = originColor;

        return grid;
    }

    private static void dfs(int[][] grid, int row, int col, int color, int originColor) {
        if (visited[row][col]) {
            return;
        }

        if (grid[row][col] != originColor) {
            return;
        }

        visited[row][col] = true;

        grid[row][col] = color;

        if (row - 1 >= 0) {
            dfs(grid, row - 1, col, color, originColor);
        }

        if (row + 1 < grid.length) {
            dfs(grid, row + 1, col, color, originColor);
        }

        if (col - 1 >= 0) {
            dfs(grid, row, col - 1, color, originColor);
        }

        if (col + 1 < grid[0].length) {
            dfs(grid, row, col + 1, color, originColor);
        }

    }

}
