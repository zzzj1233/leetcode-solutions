package com.zzzj.backtracking;

/**
 * @author zzzj
 * @create 2022-02-14 10:23
 */
public class Leet1219 {

    /**
     * 你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，
     * 并用大小为 m * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。
     * <p>
     * 为了使收益最大化，矿工需要按以下规则来开采黄金：
     * <p>
     * 每当矿工进入一个单元，就会收集该单元格中的所有黄金。
     * 矿工每次可以从当前位置向上下左右四个方向走。
     * 每个单元格只能被开采（进入）一次。
     * 不得开采（进入）黄金数目为 0 的单元格。
     * 矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
     */
    public static int ans;

    public static void main(String[] args) {
        System.out.println(getMaximumGold(new int[][]{{0, 6, 0}, {5, 8, 7}, {0, 9, 0}}));
        System.out.println(getMaximumGold(new int[][]{{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9, 0, 20}}));
    }

    public static int getMaximumGold(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        ans = 0;

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                visited[i][j] = true;
                process(grid, visited, i, j, 0);
                visited[i][j] = false;
            }
        }

        return ans;
    }

    public static void process(int[][] grid, boolean[][] visited, int row, int col, int cur) {
        cur += grid[row][col];

        ans = Math.max(ans, cur);

        // 上下左右
        if (row - 1 >= 0 && !visited[row - 1][col] && grid[row - 1][col] != 0) {
            visited[row - 1][col] = true;
            process(grid, visited, row - 1, col, cur);
            visited[row - 1][col] = false;
        }

        if (row + 1 < grid.length && !visited[row + 1][col] && grid[row + 1][col] != 0) {
            visited[row + 1][col] = true;
            process(grid, visited, row + 1, col, cur);
            visited[row + 1][col] = false;
        }

        if (col - 1 >= 0 && !visited[row][col - 1] && grid[row][col - 1] != 0) {
            visited[row][col - 1] = true;
            process(grid, visited, row, col - 1, cur);
            visited[row][col - 1] = false;
        }

        if (col + 1 < grid[row].length && !visited[row][col + 1] && grid[row][col + 1] != 0) {
            visited[row][col + 1] = true;
            process(grid, visited, row, col + 1, cur);
            visited[row][col + 1] = false;
        }

    }

}
