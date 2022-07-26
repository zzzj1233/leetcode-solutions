package com.zzzj.backtracking;

import com.zzzj.leet.LeetUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-02-17 15:13
 */
public class Leet425 {

    public static void main(String[] args) {

        Solution solution = new Solution();

        for (int i = 0; i < 10000; i++) {
            int N = LeetUtils.random.nextInt(100) + 1;
            String[] words = new String[N];
            for (int j = 0; j < N; j++) {
                words[j] = LeetUtils.randomString(4, false);
            }
            if (solution.wordSquares(words).size() != wordSquares(words).size()) {
                System.out.println("Error");
                System.out.println(Arrays.stream(words).map(s -> "\"" + s + "\"").collect(Collectors.joining(",")));
                System.out.println(solution.wordSquares(words).size());
                System.out.println(wordSquares(words).size());
                return;
            }
        }
        System.out.println("Ok");
    }

    public static List<List<String>> wordSquares(String[] words) {

        final int N = words.length;

        int wordLen = words[0].length();

        Trie[] root = new Trie[4];

        for (int i = 0; i < wordLen; i++) {
            Trie node = new Trie();
            root[i] = node;

            for (int j = 0; j < N; j++) {
                String word = words[j];
                char c = word.charAt(i);
                int index = c - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new Trie();
                }
                node.next[index].wordsIndexes.add(j);
            }
        }

        // 第N个单词是否可以作为第N个单词
        boolean[][] allow = new boolean[N][wordLen];

        for (int i = 0; i < N; i++) {
            String word = words[i];

            boolean[] indexedAllow = allow[i];

            Arrays.fill(indexedAllow, true);

            // 例如abcd想作为第二个单词,那么words必须包含 xaxx,xbxx,xcxx,xdxx
            for (int j = 0; j < wordLen; j++) {
                int index2 = word.charAt(j) - 'a';

                for (int k = 0; k < wordLen; k++) {
                    if (root[k].next[index2] == null) {
                        indexedAllow[k] = false;
                    }
                }
            }
        }

        List<List<String>> ans = new ArrayList<>();

        // 1. 所有可以作为第一个单词的
        LinkedList<String> path = new LinkedList<>();

        Rule[][] rules = new Rule[4][4];
        rules[2][1] = new Rule(1, 2);
        rules[3][1] = new Rule(1, 3);
        rules[3][2] = new Rule(2, 3);

        for (int i = 0; i < words.length; i++) {
            if (!allow[i][0]) {
                continue;
            }
            String word = words[i];
            path.add(word);
            dfs(ans, path, root, words, 1, wordLen, allow, rules);
            path.removeLast();
        }

        return ans;
    }

    public static void dfs(List<List<String>> ans,
                           LinkedList<String> path,
                           Trie[] root,
                           String[] words,
                           int index,
                           int wordLen,
                           boolean[][] allow,
                           Rule[][] rules
    ) {
        if (index == wordLen) {
            ans.add(new ArrayList<>(path));
            return;
        }

        Trie node = root[index];

        String firstWord = path.getFirst();

        char c = firstWord.charAt(index);

        int charIndex = c - 'a';

        final Trie firstNode = root[0];

        if (firstNode.next[charIndex] == null) {
            return;
        }

        OUTER:
        for (Integer wordIndex : firstNode.next[charIndex].wordsIndexes) {
            if (!allow[wordIndex][index]) {
                continue;
            }

            Rule[] rule = rules[index];

            String word = words[wordIndex];

            for (int i = 0; i < rule.length; i++) {
                Rule item = rule[i];
                if (item == null) {
                    continue;
                }
                char previousChar = path.get(item.pathIndex).charAt(item.charIndex);
                if (word.charAt(i) != previousChar) {
                    continue OUTER;
                }
            }

            path.add(word);
            dfs(ans, path, root, words, index + 1, wordLen, allow, rules);
            path.removeLast();
        }
    }

    private static class Rule {
        int pathIndex;
        int charIndex;

        public Rule(int pathIndex, int charIndex) {
            this.pathIndex = pathIndex;
            this.charIndex = charIndex;
        }
    }

    private static class Trie {
        Trie[] next = new Trie[26];
        Set<Integer> wordsIndexes = new HashSet<>(8);
    }

    private static class Solution {

        private static class Node {
            Node[] nexts = new Node[26];
            List<String> strs = new ArrayList<>();
        }

        void add(String word, Node cur) {
            char[] sc = word.toCharArray();
            for (char c : sc) {
                int index = c - 'a';
                cur.strs.add(word);
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
        }

        public List<List<String>> wordSquares(String[] words) {
            Node root = new Node();
            for (String word : words) {
                add(word, root);
            }

            int n = words[0].length();
            char[][] mat = new char[n][];
            List<List<String>> ans = new ArrayList<>();
            dfs(root, mat, 0, n, ans);
            return ans;
        }

        void dfs(Node root, char[][] mat, int i, int n, List<List<String>> ans) {
            if (i == n) {
                fill(ans, mat);
                return;
            }
            Node cur = root;
            for (int j = 0; j < i; j++) {
                int index = mat[j][i] - 'a';
                if (cur.nexts[index] == null) {
                    return;
                }
                cur = cur.nexts[index];
            }

            for (String word : cur.strs) {
                mat[i] = word.toCharArray();
                dfs(root, mat, i + 1, n, ans);
            }
        }

        void fill(List<List<String>> ans, char[][] mat) {
            List<String> cur = new ArrayList<>();
            for (int i = 0; i < mat.length; i++) {
                cur.add(new String(mat[i]));
            }
            ans.add(cur);
        }

    }

}
