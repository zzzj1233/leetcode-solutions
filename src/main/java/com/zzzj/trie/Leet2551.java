package com.zzzj.trie;

/**
 * @author zzzj
 * @create 2022-07-26 10:23
 */
public class Leet2551 {


    private static class Trie {

        Trie[] next = new Trie[26];

        boolean isEnd;

        /**
         * Initialize your data structure here.
         */
        public Trie() {

        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Trie node = this;

            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new Trie();
                }
                node = node.next[index];
            }
            node.isEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Trie node = this;

            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (node.next[index] == null) {
                    return false;
                }
                node = node.next[index];
            }
            return node.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Trie node = this;

            for (int i = 0; i < prefix.length(); i++) {
                int index = prefix.charAt(i) - 'a';
                if (node.next[index] == null) {
                    return false;
                }
                node = node.next[index];
            }

            return true;
        }

    }

}
