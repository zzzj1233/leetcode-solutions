package com.zzzj.backtracking;

/**
 * @author Zzzj
 * @create 2022-04-03 13:38
 */
public class Leet36 {

    public static boolean[][] ROW;
    public static boolean[][] COL;
    public static boolean[][] BLOCK;

    public static int blockIndex(int i, int j) {
        return 3 * (i / 3) + j / 3;
    }

    public static boolean isValidSudoku(char[][] board) {
        ROW = new boolean[9][10];
        COL = new boolean[9][10];
        BLOCK = new boolean[9][10];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    if (ROW[i][num]) {
                        return false;
                    }
                    if (COL[j][num]) {
                        return false;
                    }
                    if (BLOCK[blockIndex(i, j)][num]) {
                        return false;
                    }
                    ROW[i][num] = true;
                    COL[j][num] = true;
                    BLOCK[blockIndex(i, j)][num] = true;
                }

            }
        }

        return true;
    }

}
