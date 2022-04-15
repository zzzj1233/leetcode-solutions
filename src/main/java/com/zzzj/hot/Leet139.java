package com.zzzj.hot;

import java.util.List;

/**
 * @author zzzj
 * @create 2022-04-15 11:32
 */
public class Leet139 {

    public static void main(String[] args) {

    }

    static class Trie {
        final Node head;

        Trie(Node head) {
            this.head = head;
        }

        public boolean exists(char[] chars, int i, int j) {
            Node node = head;
            while (i <= j) {
                char c = chars[i];
                if (node.next[c - 'a'] == null) {
                    return false;
                }
                node = node.next[c - 'a'];
                i++;
            }
            return node.end;
        }

    }

    static class Node {
        boolean end;
        Node[] next;
        char c;

        public Node(char c) {
            this.next = new Node[26];
        }

    }

    public static Trie buildTrie(List<String> wordDict) {
        Node head = new Node(' ');

        for (String word : wordDict) {
            Node node = head;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new Node(c);
                }
                node = node.next[c - 'a'];
                if (i == word.length() - 1) {
                    node.end = true;
                }
            }
        }

        return new Trie(head);
    }

    // 动态规划 + trie树
    public static boolean wordBreak(String s, List<String> wordDict) {
        Trie trie = buildTrie(wordDict);
        return dp(s.toCharArray(), trie) > 0;
    }

    public static int dp(char[] chars, Trie trie) {
        int N = chars.length;

        int[] dp = new int[N + 1];

        dp[N] = 1;

        for (int n = N - 1; n >= 0; n--) {

            for (int i = n; i < N; i++) {
                if (trie.exists(chars, n, i)) {
                    dp[n] += dp[n + 1];
                }
            }

        }

        return dp[0];
    }

    public static int dfs(char[] chars, int index, Trie trie) {
        if (index == chars.length) {
            return 1;
        }
        int result = 0;
        for (int i = index; i < chars.length; i++) {
            if (trie.exists(chars, index, i)) {
                result += dfs(chars, i + 1, trie);
            }
        }

        return result;
    }

}
