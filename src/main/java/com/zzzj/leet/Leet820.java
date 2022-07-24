package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-07-22 21:38
 */
public class Leet820 {

    public static int minimumLengthEncoding(String[] words) {

        Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());

        Trie root = new Trie();

        int ans = 0;

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            if (contains(root, word)) {
                continue;
            }

            Trie node = root;

            // build trie
            for (int j = word.length() - 1; j >= 0; j--) {
                char c = word.charAt(j);

                int index = c - 'a';

                if (node.next[index] == null) {
                    node.next[index] = new Trie();
                }
                node = node.next[index];
            }

            node.isEnd = true;

            ans += word.length() + 1;
        }

        return ans;
    }

    public static boolean contains(Trie trie, String word) {
        Trie node = trie;
        for (int j = word.length() - 1; j >= 0; j--) {
            int index = word.charAt(j) - 'a';
            if (node.next[index] == null) {
                return false;
            }
            node = node.next[index];
        }
        return true;
    }

    private static class Trie {
        Trie[] next = new Trie[26];
        boolean isEnd;
    }

}
