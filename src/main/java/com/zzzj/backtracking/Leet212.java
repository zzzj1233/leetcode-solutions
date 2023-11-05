package com.zzzj.backtracking;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-07-21 15:20
 */
public class Leet212 {

    public static void main(String[] args) {
        System.out.println(findWords(LeetUtils.convertChars("[[\"o\",\"a\",\"a\",\"n\"],[\"e\",\"t\",\"a\",\"e\"],[\"i\",\"h\",\"k\",\"r\"],[\"i\",\"f\",\"l\",\"v\"]]"), new String[]{"oath", "pea", "eat", "rain"}));
    }

    public static List<String> findWords(char[][] board, String[] words) {

        Trie trie = buildTrie(words);

        int M = board.length;

        int N = board[0].length;

        boolean[][] visited = new boolean[M][N];

        Set<String> results = new HashSet<>(words.length);

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                Trie node = trie.next[board[i][j] - 'a'];

                if (node != null) {
                    visited[i][j] = true;
                    dfs(results, node, i, j, visited, board);
                    visited[i][j] = false;
                }

            }

        }

        return new ArrayList<>(results);
    }

    private static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    private static void dfs(Set<String> result, Trie node, int row, int col, boolean[][] visited, char[][] board) {
        if (node == null)
            return;

        if (node.isEnd()) {
            result.add(node.word);
        }

        for (int[] dir : DIRS) {
            int r = dir[0] + row;
            int c = dir[1] + col;

            if (r >= 0 && r < visited.length && c >= 0 && c < visited[r].length && !visited[r][c]
                    && node.next[board[r][c] - 'a'] != null
            ) {

                visited[r][c] = true;
                dfs(result, node.next[board[r][c] - 'a'], r, c, visited, board);
                visited[r][c] = false;
            }

        }

    }

    private static Trie buildTrie(String[] words) {

        Trie root = new Trie();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            Trie curr = root;

            for (int j = 0; j < word.length(); j++) {
                int index = word.charAt(j) - 'a';
                if (curr.next[index] == null)
                    curr.next[index] = new Trie();
                curr = curr.next[index];
            }

            curr.word = word;
        }

        return root;
    }

    private static class Trie {

        private Trie[] next = new Trie[26];

        private String word;

        private boolean isEnd() {
            return word != null;
        }
    }

}
