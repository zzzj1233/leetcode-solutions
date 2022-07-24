package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-07-22 21:55
 */
public class Leet2698 {

    public static void main(String[] args) {
        System.out.println(longestWord(new String[]{"cat", "banana", "dog", "nana", "walk", "walker", "dogwalker"}));
    }

    public static String longestWord(String[] words) {

        Arrays.sort(words, (o1, o2) -> {
            if (o2.length() == o1.length()) {
                return o1.compareTo(o2);
            }
            return o2.length() - o1.length();
        });

        Trie root = new Trie();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            Trie node = root;

            for (int j = 0; j < word.length(); j++) {

                char c = word.charAt(j);

                int index = c - 'a';

                if (node.next[index] == null) {
                    node.next[index] = new Trie();
                }

                node = node.next[index];
            }

            node.end = true;
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            // 看看能是否凑出两个单词匹配
            if (canMatch(root, word, 0, 0)) {
                return word;
            }
        }

        return "";
    }

    public static boolean canMatch(Trie root, String word, int index, int matchCount) {
        Trie node = root;

        for (int i = index; i < word.length(); i++) {

            int charIndex = word.charAt(i) - 'a';

            if (node.next[charIndex] == null) {
                return false;
            }

            node = node.next[charIndex];

            if (node.end && canMatch(root, word, i + 1, 1)) {
                return true;
            }
        }

        return matchCount == 1 && node.end;
    }

    private static class Trie {
        Trie[] next = new Trie[256];
        boolean end;
    }

}
