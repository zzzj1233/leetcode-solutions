package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2021-06-17 17:03
 */
public class Trie {

    private TrieNode root = new TrieNode('r');

    private int size;

    public void add(String str) {
        TrieNode node = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int idx = c - 97;
            if (node.adj[idx] == null) {
                node.adj[idx] = new TrieNode(c);
                node = node.adj[idx];
            } else {
                node = node.adj[idx];
            }
        }
        if (!node.isWord) {
            node.isWord = true;
            size++;
        }
    }

    public boolean contains(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int idx = c - 97;
            if (node.adj[idx] == null) {
                return false;
            }
            node = node.adj[idx];
        }
        return true;
    }

    private static class TrieNode {
        private char c;
        private TrieNode[] adj;
        private boolean isWord;

        public TrieNode(char c) {
            this.c = c;
            this.adj = new TrieNode[26];
        }

    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("hello");
        trie.add("hedge");
        trie.add("age");

        System.out.println(trie.contains("he"));
        System.out.println(trie.contains("hed"));
        System.out.println(trie.contains("hedge"));
        System.out.println(trie.contains("age"));

        System.out.println(trie.contains("e"));
        System.out.println(trie.contains("ed"));
        System.out.println(trie.contains("edge"));
        System.out.println(trie.contains("helloworld"));
    }

}
