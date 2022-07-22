package com.zzzj.leet;

import java.util.List;

/**
 * @author zzzj
 * @create 2022-07-20 16:04
 */
public class Leet648 {

    private static class Trie {
        Trie[] next;
        boolean isEnd;

        public Trie() {
            next = new Trie[26];
        }
    }

    public static String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();

        for (int i = 0; i < dictionary.size(); i++) {
            String word = dictionary.get(i);

            Trie node = trie;

            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);

                Trie next = node.next[c - 'a'];
                if (next == null) {
                    next = new Trie();
                    node.next[c - 'a'] = next;
                }
                node = next;
            }
            node.isEnd = true;
        }

        String[] words = sentence.split(" ");

        StringBuilder builder = new StringBuilder();

        OUTER:
        for (String word : words) {
            Trie node = trie.next[word.charAt(0) - 'a'];

            if (node == null) {
                builder.append(word);
                builder.append(" ");
                continue;
            }

            if (node.isEnd) {
                builder.append(word.charAt(0));
                builder.append(" ");
                continue;
            }

            for (int i = 1; i < word.length(); i++) {
                node = node.next[word.charAt(i) - 'a'];
                if (node == null) {
                    break;
                } else if (node.isEnd) {
                    builder.append(word, 0, i + 1);
                    builder.append(" ");
                    continue OUTER;
                }
            }

            builder.append(word);
            builder.append(" ");
        }

        if (builder.length() > 0){
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

}
