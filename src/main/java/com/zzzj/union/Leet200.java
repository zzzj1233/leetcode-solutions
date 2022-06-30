package com.zzzj.union;

/**
 * @author Zzzj
 * @create 2021-11-13 20:26
 */
public class Leet200 {

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;


        int count = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    infect(i, j, n, m, grid);
                    count++;
                }
            }

        }

        return count;
    }

    private static void infect(int i, int j, int n, int m, char[][] grid) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return;
        }

        if (grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '2';

        infect(i, j + 1, n, m, grid);
        infect(i + 1, j, n, m, grid);
        infect(i - 1, j, n, m, grid);
        infect(i, j - 1, n, m, grid);
    }

}
