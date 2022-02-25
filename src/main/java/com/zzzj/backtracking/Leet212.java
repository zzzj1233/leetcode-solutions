package com.zzzj.backtracking;

import com.zzzj.leet.LeetUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-02-25 17:55
 */
public class Leet212 {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            char[][] board = LeetUtils.random2DChars(5, 5, false);

            int wordLength = LeetUtils.random.nextInt(100) + 1;

            String[] wordsArr = new String[wordLength];

            for (int j = 0; j < wordLength; j++) {
                wordsArr[j] = LeetUtils.randomString(LeetUtils.random.nextInt(10) + 1, false);
            }

            Set<String> ans = new HashSet<>(findWords(board, wordsArr));

            if (!ans.equals(new HashSet<>(right(board, wordsArr)))) {
                System.out.println(Arrays.deepToString(board));
                System.out.println(Arrays.toString(wordsArr));
                System.out.println(ans);
                return;
            }

        }


    }

    public static List<String> findWords(char[][] board, String[] wordsArr) {

        Set<String> words = new HashSet<>(Arrays.asList(wordsArr));

        boolean[][] used = new boolean[board.length][board[0].length];

        List<String> ans = new ArrayList<>();

        Set<Integer> length = words.stream().map(String::length).collect(Collectors.toSet());

        int max = length.stream().mapToInt(value -> value).max().getAsInt();

        StringBuilder builder = new StringBuilder(max);

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[i].length; j++) {
                dfs(ans, board, used, words, length, i, j, max, builder);
            }

        }


        return ans;
    }

    public static void dfs(List<String> ans,
                           char[][] board,
                           boolean[][] used,
                           Set<String> words,
                           Set<Integer> length,
                           int i,
                           int j,
                           int maxLength,
                           StringBuilder path
    ) {
        if (used[i][j]) {
            return;
        }

        String str;

        int len = path.length();

        if (len == maxLength) {
            return;
        }

        boolean match = false;

        path.append(board[i][j]);
        used[i][j] = true;

        if (length.contains(len + 1) && words.contains((str = path.toString()))) {
            ans.add(str);
            path.setLength(len);
            match = true;
        }
        if (i - 1 >= 0) {
            dfs(ans, board, used, words, length, i - 1, j, maxLength, path);
        }
        if (i + 1 < board.length) {
            dfs(ans, board, used, words, length, i + 1, j, maxLength, path);
        }
        if (j - 1 >= 0) {
            dfs(ans, board, used, words, length, i, j - 1, maxLength, path);
        }
        if (j + 1 < board[i].length) {
            dfs(ans, board, used, words, length, i, j + 1, maxLength, path);
        }
        used[i][j] = false;
        if (!match) {
            path.setLength(len);
        }
    }


    // 上下左右移动的方向
    static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static List<String> right(char[][] board, String[] words) {
        // 结果集，去重
        Set<String> resultSet = new HashSet<>();

        // 构建字典树
        TrieNode root = buildTrie(words);

        int m = board.length;
        int n = board[0].length;
        // 记录某个下标是否访问过
        boolean[][] visited = new boolean[m][n];
        // 记录沿途遍历到的元素
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // 从每个元素开始遍历
                dfs(resultSet, result, board, i, j, root, visited);
            }
        }

        // 题目要求返回List
        return new ArrayList<>(resultSet);
    }

    private static void dfs(Set<String> resultSet, StringBuilder result, char[][] board,
                            int i, int j, TrieNode node, boolean[][] visited) {
        // 判断越界，或者访问过，或者不在字典树中，直接返回
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]
                || node.children[board[i][j] - 'a'] == null) {
            return;
        }

        // 记录当前字符
        result.append(board[i][j]);

        // 如果有结束字符，加入结果集中
        if (node.children[board[i][j] - 'a'].isEnd) {
            resultSet.add(result.toString());
        }

        // 记录当前元素已访问
        visited[i][j] = true;

        // 按四个方向去遍历
        for (int[] dir : dirs) {
            dfs(resultSet, result, board, i + dir[0], j + dir[1], node.children[board[i][j] - 'a'], visited);
        }

        // 还原状态
        visited[i][j] = false;
        result.deleteCharAt(result.length() - 1);
    }

    private static TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            char[] arr = word.toCharArray();
            TrieNode curr = root;
            for (char c : arr) {
                if (curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new TrieNode();
                }
                curr = curr.children[c - 'a'];
            }
            curr.isEnd = true;
        }
        return root;
    }

    static class TrieNode {
        // 记录到这个节点是否是一个完整的单词
        boolean isEnd = false;
        // 孩子节点，题目说了都是小写字母，所以用数组，否则可以用HashMap替换
        TrieNode[] children = new TrieNode[26];
    }


}
