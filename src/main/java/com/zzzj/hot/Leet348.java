package com.zzzj.hot;

/**
 * @author zzzj
 * @create 2022-05-06 15:11
 */
public class Leet348 {

    static class TicTacToe {

        private final int[][] row;

        private final int[][] col;

        private final int[] lr;

        private final int[] rl;

        private final boolean[][] visited;

        private final int end;

        public TicTacToe(int n) {
            // n * n 的矩阵
            row = new int[n][3];
            col = new int[n][3];
            lr = new int[3];
            rl = new int[3];
            visited = new boolean[n][n];
            end = n;
        }

        // 0 = 平局
        // 1 = 玩家1
        // 2 = 玩家2
        public int move(int row, int col, int player) {
            if (visited[row][col]) {
                return 0;
            }
            visited[row][col] = true;

            if (++this.row[row][player] == end) {
                return player;
            }

            if (++this.col[col][player] == end) {
                return player;
            }

            // 是否是左对角线
            if (row == col) {
                if (++lr[player] == end) {
                    return player;
                }
            }
            // 是否是右对角线
            if (row + col == end - 1) {
                if (++rl[player] == end) {
                    return player;
                }
            }

            return 0;
        }

    }


}
