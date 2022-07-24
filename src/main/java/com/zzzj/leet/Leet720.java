package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-07-23 22:28
 */
public class Leet720 {

    public static void main(String[] args) {
        System.out.println(longestWord(new String[]{"w", "wo", "wor", "worl", "world"}));
    }


    public static String longestWord(String[] words) {

        Arrays.sort(words, (o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o2.compareTo(o1);
            }
            return o1.length() - o2.length();
        });


        Trie root = new Trie();

        int ans = -1;

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            Trie node = root;

            boolean allMatch = true;

            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);

                int index = c - 'a';

                if (j < word.length() - 1) {
                    if (node.next[index] == null) {
                        node.next[index] = new Trie();
                        allMatch = false;
                    }
                    allMatch &= node.next[index].isEnd;
                } else {
                    if (node.next[index] == null) {
                        node.next[index] = new Trie();
                    }
                }

                node = node.next[index];
            }

            node.isEnd = true;

            if (allMatch) {
                ans = i;
            }

        }

        return ans == -1 ? "" : words[ans];
    }

    private static class Trie {
        Trie[] next = new Trie[26];
        boolean isEnd;
    }

}
