package com.zzzj.backtracking;

/**
 * @author zzzj
 * @create 2022-03-29 11:37
 */
public class Leet2280 {

    public static boolean ans;

    public static boolean exist(char[][] board, String word) {
        ans = false;

        char[] chars = word.toCharArray();

        final char firstChar = chars[0];

        final boolean[][] used = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == firstChar) {
                    dfs(board, chars, 0, used, i, j);
                }
            }
        }


        return false;
    }

    public static void dfs(char[][] board, char[] word, int index, boolean[][] used, int i, int j) {
        if (ans || used[i][j]) {
            return;
        }
        if (index == word.length) {
            ans = true;
            return;
        }
        used[i][j] = true;

        final char next = word[index + 1];

        // 上下左右
        if (i - 1 >= 0 && board[i - 1][j] == next && !used[i - 1][j]) {
            dfs(board, word, index + 1, used, i - 1, j);
        }

        if (i + 1 < board.length && board[i + 1][j] == next && !used[i + 1][j]) {
            dfs(board, word, index + 1, used, i + 1, j);
        }

        if (j - 1 >= 0 && board[i][j - 1] == next && !used[i][j - 1]) {
            dfs(board, word, index + 1, used, i, j - 1);
        }

        if (j + 1 < board[i].length && board[i][j + 1] == next && !used[i][j + 1]) {
            dfs(board, word, index + 1, used, i, j + 1);
        }

        used[i][j] = false;
    }

}
