package com.zzzj.leet;

import java.util.HashSet;

/**
 * @author Zzzj
 * @create 2022-07-23 22:42
 */
public class Leet676 {


    public static void main(String[] args) {
        // ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
        // [[], [["hello","hallo","leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]

        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "hallo", "leetcode"});

        System.out.println(magicDictionary.search("hell"));

    }

    private static class MagicDictionary {

        private Trie root;

        private HashSet<Object> len;

        public MagicDictionary() {

        }

        public void buildDict(String[] dictionary) {

            this.root = new Trie();

            this.len = new HashSet<>(dictionary.length);

            for (int i = 0; i < dictionary.length; i++) {
                String word = dictionary[i];

                Trie node = root;

                for (int j = 0; j < word.length(); j++) {
                    char c = word.charAt(j);

                    int index = c - 'a';

                    if (node.next[index] == null) {
                        node.next[index] = new Trie();
                    }

                    node = node.next[index];
                }

                len.add(word.length());

                node.isEnd = true;
            }

        }

        public boolean search(String searchWord) {
            if (!len.contains(searchWord.length())){
                return false;
            }
            return search(this.root, 0, searchWord, true);
        }

        private static boolean search(Trie node, int index, String word, boolean allow) {
            for (int i = index; i < word.length(); i++) {
                char c = word.charAt(i);

                int charIndex = c - 'a';

                if (node.next[charIndex] == null) {
                    if (!allow) {
                        return false;
                    }

                    for (int j = 0; j < node.next.length; j++) {

                        if (node.next[j] != null && search(node.next[j], i + 1, word, false)) {
                            return true;
                        }

                    }

                    return false;
                }

                if (allow) {
                    for (int j = 0; j < node.next.length; j++) {

                        if (j != charIndex && node.next[j] != null && search(node.next[j], i + 1, word, false)) {
                            return true;
                        }

                    }
                }

                node = node.next[charIndex];
            }

            return !allow && node.isEnd;
        }

        private static class Trie {
            Trie[] next = new Trie[26];
            boolean isEnd;
        }

    }

}
