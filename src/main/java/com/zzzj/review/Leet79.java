package com.zzzj.review;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2023-03-15 18:10
 */
public class Leet79 {

    public static void main(String[] args) {
        System.out.println(exist(LeetUtils.convertChars("[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]"), "ABCCED"));
    }

    public static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    public static boolean exist(char[][] board, String word) {

        int M = board.length;

        int N = board[0].length;

        boolean[][] visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                if (
                        dfs(board,
                                i,
                                j,
                                word,
                                0,
                                visited)
                ) return true;

            }

        }

        return false;
    }

    public static boolean dfs(char[][] board,
                              int row,
                              int col,
                              String word,
                              int wordIndex,
                              boolean[][] visited) {
        if (wordIndex >= word.length()) {
            return true;
        }

        char c = word.charAt(wordIndex);

        if (visited[row][col]) {
            return false;
        }

        if (board[row][col] != c) {
            return false;
        }

        visited[row][col] = true;

        boolean any = wordIndex + 1 == word.length();

        for (int[] dir : DIRS) {
            int x = dir[0];
            int y = dir[1];

            if (x + row >= 0 && x + row < board.length && y + col >= 0 && y + col < board[0].length && !visited[x + row][y + col]) {
                any |= dfs(board, x + row, y + col, word, wordIndex + 1, visited);
            }

        }

        visited[row][col] = false;

        return any;
    }


}
