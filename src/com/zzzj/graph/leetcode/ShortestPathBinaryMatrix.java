package com.zzzj.graph.leetcode;

import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2021-05-02 16:52
 */

// 1091. 二进制矩阵中的最短路径
public class ShortestPathBinaryMatrix {

    private int[][] grid;

    private boolean[][] visited;

    private int[] distance;

    private int row;

    private int col;

    public int shortestPathBinaryMatrix(int[][] grid) {
        this.grid = grid;

        if (grid[0][0] == 1) {
            return -1;
        }

        this.row = grid.length;
        this.col = grid[0].length;

        if (row == 1 && col == 1) {
            return 0;
        }

        this.visited = new boolean[row][col];
        this.distance = new int[row * col];

        bfs(0, 0);

        int dis = distance[getIdx(row - 1, col - 1)];
        return dis == 0 ? -1 : dis + 1;
    }

    private int getIdx(int i, int j) {
        return i * this.row + j;
    }

    private void bfs(int i, int j) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(getIdx(i, j));

        while (!queue.isEmpty()) {
            int first = queue.pollFirst();

            // 上右下左
            int curI = first / col;
            int curJ = first % col;
            visited[curI][curJ] = true;

            if ((curI - 1) >= 0) {
                if (!visited[curI - 1][curJ] && grid[curI - 1][curJ] == 0) {
                    visited[curI - 1][curJ] = true;
                    int idx = getIdx(curI - 1, curJ);
                    distance[idx] = distance[first] + 1;
                    queue.addLast(idx);
                }

                // 上左
                if ((curJ - 1) >= 0 && !visited[curI - 1][curJ - 1] && grid[curI - 1][curJ - 1] == 0) {
                    visited[curI - 1][curJ - 1] = true;
                    distance[getIdx(curI - 1, curJ - 1)] = distance[first] + 1;
                    queue.addLast(getIdx(curI - 1, curJ - 1));
                }
                // 上右
                if ((curJ + 1) < row && !visited[curI - 1][curJ + 1] && grid[curI - 1][curJ + 1] == 0) {
                    visited[curI - 1][curJ + 1] = true;
                    distance[getIdx(curI - 1, curJ + 1)] = distance[first] + 1;
                    queue.addLast(getIdx(curI - 1, curJ + 1));
                }
            }

            if ((curI + 1) < row) {
                if (!visited[curI + 1][curJ] && grid[curI + 1][curJ] == 0) {
                    visited[curI + 1][curJ] = true;
                    int idx = getIdx(curI + 1, curJ);
                    distance[idx] = distance[first] + 1;
                    queue.addLast(idx);
                }

                // 下左
                if ((curJ - 1) >= 0 && !visited[curI + 1][curJ - 1] && grid[curI + 1][curJ - 1] == 0) {
                    visited[curI + 1][curJ - 1] = true;
                    distance[getIdx(curI + 1, curJ - 1)] = distance[first] + 1;
                    queue.addLast(getIdx(curI + 1, curJ - 1));
                }
                // 下右
                if ((curJ + 1) < row && !visited[curI + 1][curJ + 1] && grid[curI + 1][curJ + 1] == 0) {
                    visited[curI + 1][curJ + 1] = true;
                    distance[getIdx(curI + 1, curJ + 1)] = distance[first] + 1;
                    queue.addLast(getIdx(curI + 1, curJ + 1));
                }
            }


            if ((curJ - 1) >= 0 && !visited[curI][curJ - 1] && grid[curI][curJ - 1] == 0) {
                visited[curI][curJ - 1] = true;
                int idx = getIdx(curI, curJ - 1);
                distance[idx] = distance[first] + 1;
                queue.addLast(idx);
            }

            if ((curJ + 1) < col && !visited[curI][curJ + 1] && grid[curI][curJ + 1] == 0) {
                visited[curI][curJ + 1] = true;
                int idx = getIdx(curI, curJ + 1);
                distance[idx] = distance[first] + 1;
                queue.addLast(idx);
            }


        }
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(new ShortestPathBinaryMatrix().shortestPathBinaryMatrix(grid));
    }

}
