package com.zzzj.backtracking;

import com.zzzj.leet.LeetUtils;

import java.awt.image.Kernel;
import java.util.*;

/**
 * @author zzzj
 * @create 2022-02-28 17:44
 */
public class Leet37 {

    public static void main(String[] args) {
        final char[][] chars = LeetUtils.convertChars("[[\"5\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"],[\"6\",\".\",\".\",\"1\",\"9\",\"5\",\".\",\".\",\".\"],[\".\",\"9\",\"8\",\".\",\".\",\".\",\".\",\"6\",\".\"],[\"8\",\".\",\".\",\".\",\"6\",\".\",\".\",\".\",\"3\"],[\"4\",\".\",\".\",\"8\",\".\",\"3\",\".\",\".\",\"1\"],[\"7\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\",\"6\"],[\".\",\"6\",\".\",\".\",\".\",\".\",\"2\",\"8\",\".\"],[\".\",\".\",\".\",\"4\",\"1\",\"9\",\".\",\".\",\"5\"],[\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]]");

        solveSudoku(chars);

        System.out.println(Arrays.deepToString(chars));
    }


    public static boolean[][] ROW;
    public static boolean[][] COL;
    public static boolean[][] BLOCK;

    public static int blockIndex(int i, int j) {
        return 3 * (i / 3) + j / 3;
    }

    public static void solveSudoku(char[][] board) {
        ROW = new boolean[9][10];
        COL = new boolean[9][10];
        BLOCK = new boolean[9][10];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    ROW[i][num] = true;
                    COL[j][num] = true;
                    BLOCK[blockIndex(i, j)][num] = true;
                }

            }
        }


        dfs(board, 0, 0);
    }

    public static boolean dfs(char[][] board, int i, int j) {
        if (i == 9) {
            return true;
        }

        int nextI = j == 8 ? i + 1 : i;
        int nextJ = j == 8 ? 0 : j + 1;

        if (board[i][j] == '.') {
            for (int k = 1; k <= 9; k++) {
                if (!ROW[i][k] && !COL[j][k] && !BLOCK[blockIndex(i, j)][k]) {
                    ROW[i][k] = true;
                    COL[j][k] = true;
                    BLOCK[blockIndex(i, j)][k] = true;

                    board[i][j] = (char) (k + '0');

                    if (dfs(board, nextI, nextJ)) {
                        return true;
                    }

                    board[i][j] = '.';

                    ROW[i][k] = false;
                    COL[j][k] = false;
                    BLOCK[blockIndex(i, j)][k] = false;
                }
            }
            return false;
        } else {
            return dfs(board, nextI, nextJ);
        }
    }

}
