package com.zzzj.graph.leetcode;

/**
 * @author Zzzj
 * @create 2021-05-05 15:18
 */
// 980. 不同路径 III
public class UniquePathsIII {

    private int[][] grid;

    private int row;

    private int col;

    private int start;

    private int total;

    private int visited;

    private final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private void visit(int i, int j) {
        if (!isVisit(i, j)) {
            visited += (1 << (getIdx(i, j) + 1));
        }
    }

    private void unVisit(int i, int j) {
        if (isVisit(i, j)) {
            visited -= (1 << (getIdx(i, j) + 1));
        }
    }

    private boolean isVisit(int i, int j) {
        return (visited & (1 << (getIdx(i, j) + 1))) != 0;
    }

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        this.row = grid.length;
        this.col = grid[0].length;
        total = row * col;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 起始方格
                if (grid[i][j] == 1) {
                    start = getIdx(i, j);
                } else if (grid[i][j] == -1) {
                    total -= 1;
                }
            }
        }

        return dfs(start / col, start % col, 1);
    }

    private int getIdx(int i, int j) {
        return i * col + j;
    }

    private int dfs(int i, int j, int order) {
        if (grid[i][j] == 2 && order == total) {
            return 1;
        }

        visit(i, j);

        int total = 0;
        for (int[] dirs : dir) {
            int i1 = i + dirs[0];
            int j1 = j + dirs[1];
            if (i1 >= 0 && i1 < row && j1 >= 0 && j1 < col && grid[i1][j1] != -1 && !isVisit(i1, j1)) {
                int find = dfs(i1, j1, order + 1);
                if (find > 0) {
                    unVisit(i1, j1);
                }
                total += find;
            }
        }

        if (total == 0) {
            unVisit(i, j);
        }

        return total;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}};
        System.out.println(new UniquePathsIII().uniquePathsIII(grid));
    }
}
