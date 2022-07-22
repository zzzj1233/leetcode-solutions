package com.zzzj.backtracking;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-07-21 15:20
 */
public class Leet212 {

    public static void main(String[] args) {
        System.out.println(findWords(LeetUtils.convertChars("[[\"o\",\"a\",\"a\",\"n\"],[\"e\",\"t\",\"a\",\"e\"],[\"i\",\"h\",\"k\",\"r\"],[\"i\",\"f\",\"l\",\"v\"]]"), new String[]{"oath", "pea", "eat", "rain"}));
    }

    public static List<String> findWords(char[][] board, String[] words) {
        int N = board.length;
        int M = board[0].length;

        ArrayList<String> ans = new ArrayList<>();

        boolean[][] visited = new boolean[N][M];

        OUTER:
        for (String word : words) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (dfs(board, visited, word, 0, i, j)) {
                        ans.add(word);
                        continue OUTER;
                    }
                }
            }

        }

        return ans;
    }

    public static final int[][] DIRS = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
    };

    // 回溯,超时
    public static boolean dfs(char[][] board, boolean[][] visited, String word, int index, int i, int j) {
        if (index == word.length()) {
            return true;
        }

        if (visited[i][j]) {
            return false;
        }

        if (word.charAt(index) != board[i][j]) {
            return false;
        }

        if (index + 1 == word.length()) {
            return true;
        }

        visited[i][j] = true;

        // 上下左右
        boolean result = false;
        for (int[] dir : DIRS) {
            int row = dir[0];
            int col = dir[1];
            if (i + row >= 0 && i + row < board.length && j + col >= 0 && j + col < board[i].length && !visited[i + row][j + col]) {
                result = dfs(board, visited, word, index + 1, i + row, j + col);
                if (result) {
                    break;
                }
            }
        }

        visited[i][j] = false;

        return result;
    }

    private static class Trie {
        Trie[] next;

        public Trie() {
            next = new Trie[26];
        }

    }

}
