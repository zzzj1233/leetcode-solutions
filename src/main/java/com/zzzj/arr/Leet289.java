package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

public class Leet289 {

    public static void main(String[] args) {
        gameOfLife(LeetUtils.convertInts("[[0,1,0],[0,0,1],[1,1,1],[0,0,0]]"));
    }

    public static final int[][] DIRS2 = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public static void gameOfLife(int[][] board) {

        int M = board.length;

        int N = board[0].length;

        int[][] origin = new int[M][];

        for (int i = 0; i < M; i++) {
            origin[i] = Arrays.copyOfRange(board[i], 0, N);
        }

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                // live
                if (origin[i][j] == 1) {
                    board[i][j] = canStillAlive(i, j, origin) ? 1 : 0;
                } else { // died
                    board[i][j] = canRevive(i, j, origin) ? 1 : 0;
                }

            }

        }

    }

    public static int nearLiveCells(int x, int y, int[][] board) {
        int M = board.length;
        int N = board[x].length;

        int aliveCells = 0;

        for (int[] dir : DIRS2) {
            int row = x + dir[0];
            int col = y + dir[1];

            if (row >= 0 && col >= 0 && row < M && col < N && board[row][col] == 1) {
                aliveCells++;
            }

        }

        return aliveCells;
    }

    public static boolean canStillAlive(int x, int y, int[][] board) {
        int nearLiveCells = nearLiveCells(x, y, board);
        return nearLiveCells == 2 || nearLiveCells == 3;
    }

    public static boolean canRevive(int x, int y, int[][] board) {
        return nearLiveCells(x, y, board) == 3;
    }

}
