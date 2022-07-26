package com.zzzj.trie;

/**
 * @author zzzj
 * @create 2022-07-26 10:42
 */
public class Leet2671 {


    private static class WordsFrequency {

        Trie root = new Trie();

        public WordsFrequency(String[] book) {
            for (String word : book) {
                Trie node = this.root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    int index = c - 'a';
                    if (node.next[index] == null) {
                        node.next[index] = new Trie();
                    }
                    node = node.next[index];
                }

                node.count++;
            }

        }

        public int get(String word) {
            Trie node = this.root;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }
            return node.count;
        }

        private static class Trie {
            Trie[] next = new Trie[26];
            int count;
        }
    }

}
