package com.zzzj.leet;

import java.util.*;

/**
 * @author Zzzj
 * @create 2022-07-27 20:44
 */
public class Leet336 {

    public static void main(String[] args) {

        // 1. 全回文,可以匹配空串

        // 2. 存在 前缀/后缀 最长回文
        // 找最长回文的逆序

        // 3. 找整个字符串的逆序
        System.out.println(palindromePairs(new String[]{"a", "abc", "aba", ""}));
    }

    public static List<List<Integer>> palindromePairs(String[] words) {
        Trie root = new Trie();

        int N = words.length;

        int empty = -1;

        int oneLen = -1;

        for (int i = 0; i < N; i++) {
            String word = words[i];

            Trie node = root;

            int length = word.length();

            if (length == 0) {
                empty = i;
                continue;
            }

            if (length == 1) {
                oneLen = i;
            }

            for (int j = 0; j < length; j++) {
                int index = word.charAt(j) - 'a';

                if (node.next[index] == null) {
                    node.next[index] = new Trie();
                }

                node = node.next[index];

            }

            node.isEnd = true;
            node.index = i;
        }

        List<List<Integer>> ans = new ArrayList<>(N);

        Map<Integer, Integer> map = new HashMap<>();

        OUTER:
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            int len = word.length();

            if (len == 1) {
                if (empty != -1) {
                    ans.add(Arrays.asList(i, empty));
                    ans.add(Arrays.asList(empty, i));
                }
                continue;
            }

            if (map.containsKey(i)) {
                ans.add(Arrays.asList(i, map.get(i)));
                continue;
            }

            for (int j = 0; j < len; j++) {

                if (IsPalindrome(word, 0, j)) {

                    if (j == len - 1 && empty != -1) {
                        ans.add(Arrays.asList(i, empty));
                        ans.add(Arrays.asList(empty, i));
                        continue OUTER;
                    }

                    int search = root.search(word, len - 1, j + 1);

                    if (search != -1) {
                        ans.add(Arrays.asList(search, i));
                        continue OUTER;
                    }
                    
                }

            }

            for (int j = len - 1; j >= 0; j--) {

                if (IsPalindrome(word, len - 1, j)) {
                    int search = root.search(word, j - 1, 0);
                    if (search != -1) {
                        ans.add(Arrays.asList(i, search));
                        continue OUTER;
                    }
                }

            }

            int search = root.search(word, len - 1, 0);

            if (search != -1) {
                ans.add(Arrays.asList(i, search));
                map.put(search, i);
            }

        }


        return ans;
    }


    public static boolean IsPalindrome(String word, int i, int j) {
        if (i < j) {
            while (i < j) {
                if (word.charAt(i) != word.charAt(j)) {
                    return false;
                }
                i++;
                j--;
            }
        } else {
            while (i > j) {
                if (word.charAt(i) != word.charAt(j)) {
                    return false;
                }
                i--;
                j++;
            }
        }

        return true;
    }


    private static class Trie {
        Trie[] next = new Trie[26];
        boolean isEnd;
        int index;

        public int search(String word, int i, int j) {
            Trie node = this;

            if (i < 0 || j < 0) {
                return -1;
            }

            int len = word.length();

            // 正向搜索
            if (i <= j) {
                while (i <= j && i < len) {
                    int index = word.charAt(i) - 'a';
                    if (node.next[index] == null) {
                        return -1;
                    }
                    node = node.next[index];
                    i++;
                }
            } else {
                // 反向搜索
                while (i >= j) {
                    int index = word.charAt(i) - 'a';
                    if (node.next[index] == null) {
                        return -1;
                    }
                    node = node.next[index];
                    i--;
                }
            }

            return node.isEnd ? node.index : -1;
        }
    }

}
