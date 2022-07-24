package com.zzzj.leet;

import cn.hutool.core.text.CharSequenceUtil;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2022-07-24 20:08
 */
public class Leet1032 {

    public static void main(String[] args) {
        StreamChecker checker = new StreamChecker(new String[]{"baa", "aa", "aaaa", "abbbb", "aba"});

        String expression = "[" + CharSequenceUtil.repeatAndJoin("query", 49, ",") + "]";

        String arguments = "[[\"a\"],[\"a\"],[\"a\"],[\"b\"],[\"a\"],[\"b\"],[\"a\"],[\"a\"],[\"b\"],[\"a\"],[\"b\"],[\"a\"],[\"b\"],[\"b\"],[\"a\"],[\"a\"],[\"a\"],[\"b\"],[\"b\"],[\"a\"],[\"b\"],[\"a\"],[\"a\"],[\"a\"],[\"a\"],[\"b\"],[\"b\"],[\"a\"],[\"a\"],[\"a\"],[\"a\"],[\"a\"],[\"b\"],[\"b\"],[\"a\"],[\"b\"],[\"a\"],[\"a\"],[\"b\"],[\"b\"],[\"b\"],[\"b\"],[\"b\"],[\"b\"],[\"b\"],[\"b\"],[\"b\"],[\"b\"],[\"a\"],[\"a\"]]";

        System.out.println(LeetUtils.executeExpression(expression, arguments, checker));
    }

    private static class StreamChecker {

        private final Trie root;

        private final LinkedList<Trie> nodes = new LinkedList<>();

        public StreamChecker(String[] words) {

            this.root = new Trie();

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

                node.isEnd = true;
            }
        }

        public boolean query(char letter) {
            int index = letter - 'a';

            Iterator<Trie> iterator = nodes.iterator();

            boolean result = false;

            LinkedList<Trie> addition = new LinkedList<>();

            while (iterator.hasNext()) {
                Trie next = iterator.next();
                iterator.remove();

                if (next.next[index] == null) {
                    continue;
                }

                if (next.next[index].isEnd) {
                    result = true;
                }

                addition.add(next.next[index]);
            }

            nodes.addAll(addition);

            if (root.next[index] != null) {
                if (root.next[index].isEnd) {
                    result = true;
                }
                nodes.add(root.next[index]);
            }

            return result;
        }

        private static class Trie {
            Trie[] next = new Trie[26];
            boolean isEnd;
        }

    }
}
