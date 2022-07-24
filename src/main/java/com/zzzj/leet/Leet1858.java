package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-07-23 23:50
 */
public class Leet1858 {

    public static void main(String[] args) {
        System.out.println(longestWord(new String[]{"k", "ki", "kir", "kira", "kiran"}));
    }

    public static String longestWord(String[] words) {
        Trie root = new Trie();

        Arrays.sort(words, (o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o2.compareTo(o1);
            }
            return o1.length() - o2.length();
        });

        int ans = -1;

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            Trie node = root;

            boolean match = true;

            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);

                int index = c - 'a';

                if (j < word.length() - 1) {

                    if (node.next[index] == null) {
                        node.next[index] = new Trie();
                        match = false;
                    }

                    node = node.next[index];

                    if (!node.isEnd) {
                        match = false;
                    }
                } else {
                    if (node.next[index] == null) {
                        node.next[index] = new Trie();
                    }
                    node = node.next[index];
                }

            }

            if (match) {
                ans = i;
            }

            node.isEnd = true;
        }

        return ans == -1 ? "" : words[ans];
    }

    private static class Trie {
        Trie[] next = new Trie[26];
        boolean isEnd;
    }

}
