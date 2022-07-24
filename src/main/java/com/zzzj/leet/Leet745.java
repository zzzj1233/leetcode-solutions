package com.zzzj.leet;

import java.util.TreeSet;

/**
 * @author Zzzj
 * @create 2022-07-24 17:10
 */
public class Leet745 {

    public static void main(String[] args) {
        WordFilter wordFilter = new WordFilter(new String[]{"apple"});

        System.out.println(wordFilter.f("b", "e"));
    }

    private static class WordFilter {

        private final Trie perRoot;

        private final Trie tailRoot;

        public WordFilter(String[] words) {

            this.perRoot = new Trie();
            this.tailRoot = new Trie();

            for (int i = 0; i < words.length; i++) {
                String word = words[i];

                Trie node = this.perRoot;

                for (int j = 0; j < word.length(); j++) {

                    int index = word.charAt(j) - 'a';

                    if (node.next[index] == null) {
                        node.next[index] = new Trie();
                    }
                    node = node.next[index];
                    node.indexes.add(i);
                }

                node = this.tailRoot;

                for (int j = word.length() - 1; j >= 0; j--) {
                    int index = word.charAt(j) - 'a';

                    if (node.next[index] == null) {
                        node.next[index] = new Trie();
                    }
                    node = node.next[index];
                    node.indexes.add(i);
                }

            }
        }

        public int f(String pref, String suff) {
            String suffix = new StringBuilder(suff).reverse().toString();

            Trie preTrie = search(this.perRoot, pref);

            if (preTrie == null) {
                return -1;
            }

            Trie tailTrie = search(this.tailRoot, suffix);

            if (tailTrie == null) {
                return -1;
            }

            TreeSet<Integer> set1 = preTrie.indexes;
            TreeSet<Integer> set2 = tailTrie.indexes;

            int ans = 0;

            for (Integer index : set1) {
                if (set2.contains(index)) {
                    return index;
                }
            }

            return -1;
        }

        public static Trie search(Trie root, String word) {
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (root.next[index] == null) {
                    return null;
                }
                root = root.next[index];
            }

            return root;
        }

        private static class Trie {
            Trie[] next = new Trie[26];
            TreeSet<Integer> indexes = new TreeSet<>((o1, o2) -> o2 - o1);
        }


    }

}
