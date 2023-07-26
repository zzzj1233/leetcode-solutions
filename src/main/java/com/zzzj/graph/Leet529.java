package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

public class Leet529 {

    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(updateBoard(LeetUtils.convertChars("[[\"E\",\"E\",\"E\",\"E\",\"E\"],[\"E\",\"E\",\"M\",\"E\",\"E\"],[\"E\",\"E\",\"M\",\"E\",\"E\"],[\"E\",\"E\",\"E\",\"E\",\"E\"]]"), new int[]{3, 0})));

    }

    public static char[][] updateBoard(char[][] board, int[] click) {

        int M = board.length;

        int N = board[0].length;

        int x = click[0];

        int y = click[1];

        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }

        dfs(board, x, y, new boolean[M][N]);

        return board;
    }

    public static final int[][] DIRS2 = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public static void dfs(char[][] board, int x, int y, boolean[][] visited) {

        if (visited[x][y]) return;

        visited[x][y] = true;

        // 如果周围全都没有炸弹, 那么全部要递归得揭开

        int cnt = 0;

        for (int[] dir : DIRS2) {

            int r = dir[0] + x;

            int c = dir[1] + y;

            if (r >= 0 && c >= 0 && r < board.length && c < board[r].length && board[r][c] == 'M') {
                cnt += board[r][c] == 'M' ? 1 : 0;
            }

        }

        if (cnt != 0) {
            if (!Character.isDigit(board[x][y]))
                board[x][y] = Character.forDigit(cnt, 10);
            return;
        }

        board[x][y] = 'B';

        for (int[] dir : DIRS2) {

            int r = dir[0] + x;

            int c = dir[1] + y;

            if (r >= 0 && c >= 0 && r < board.length && c < board[r].length)
                dfs(board, r, c, visited);
        }

    }

}
