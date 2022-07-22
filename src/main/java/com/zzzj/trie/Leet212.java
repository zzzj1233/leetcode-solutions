package com.zzzj.trie;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-07-21 15:56
 */
public class Leet212 {

    public static void main(String[] args) {
        System.out.println(findWords(LeetUtils.convertChars("[[\"a\",\"a\"],[\"c\",\"d\"]]"), new String[]{"ac", "ad", "aac"}));
    }

    public static List<String> findWords(char[][] board, String[] words) {
        Trie trie = buildTrie(board);

        ArrayList<String> ans = new ArrayList<>();

        for (String word : words) {
            if (search(word, trie)) {
                ans.add(word);
            }
        }

        return ans;
    }


    private static boolean search(String word, Trie trie) {
        Trie node = trie;


        int i = 0;
        for (; i < word.length(); i++) {
            char c = word.charAt(i);

            node = node.next[c - 'a'];

            if (node == null) {
                break;
            }
        }

        if (i == word.length()) {
            return true;
        }

        i = word.length() - 1;

        node = trie;

        for (; i >= 0; i--) {
            char c = word.charAt(i);

            if (node == null) {
                return false;
            }

            node = node.next[c - 'a'];
        }

        return true;
    }

    // 构建trie
    private static Trie buildTrie(char[][] board) {
        Trie root = new Trie();


        int N = board.length;
        int M = board[0].length;


        Trie[][] dp = new Trie[N][M];
        dp[0][0] = new Trie();

        for (int i = 1; i < N; i++) {
            Trie trie = new Trie();
            trie.next[board[i - 1][0] - 'a'] = dp[i - 1][0];
            dp[i][0] = trie;

            root.next[board[i][0] - 'a'] = trie;
        }

        for (int i = 1; i < M; i++) {
            Trie trie = new Trie();
            trie.next[board[0][i - 1] - 'a'] = dp[0][i - 1];
            dp[0][i] = trie;

            root.next[board[0][i] - 'a'] = trie;
        }

        for (int i = 1; i < N; i++) {

            for (int j = 1; j < M; j++) {
                char topChar = board[i - 1][j];
                char leftChar = board[i][j - 1];

                Trie trie = new Trie();

                if (topChar == leftChar) {
                    trie.next[topChar - 'a'] = merge(dp[i - 1][j], dp[i][j - 1]);
                } else {
                    trie.next[topChar - 'a'] = dp[i - 1][j];
                    trie.next[leftChar - 'a'] = dp[i][j - 1];
                }

                char c = board[i][j];

                if (root.next[c - 'a'] != null) {
                    root.next[c - 'a'] = merge(root.next[c - 'a'], trie);
                } else {
                    root.next[c - 'a'] = trie;
                }

                dp[i][j] = root.next[c - 'a'];
            }

        }


        return root;
    }

    private static Trie merge(Trie a, Trie b) {
        if (a == b) {
            return a;
        }

        Trie newNode = new Trie();

        for (int i = 0; i < 26; i++) {
            if (a.next[i] != null && b.next[i] != null) {
                newNode.next[i] = merge(a.next[i], b.next[i]);
            } else if (b.next[i] != null) {
                newNode.next[i] = b.next[i];
            } else if (a.next[i] != null) {
                newNode.next[i] = a.next[i];
            }
        }

        return newNode;
    }

    private static class Trie {
        Trie[] next;

        public Trie() {
            next = new Trie[26];
        }
    }

}
