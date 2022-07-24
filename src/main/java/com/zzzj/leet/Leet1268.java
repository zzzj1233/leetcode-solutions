package com.zzzj.leet;

import java.util.*;

/**
 * @author Zzzj
 * @create 2022-07-24 14:29
 */
public class Leet1268 {

    public static void main(String[] args) {
        System.out.println(suggestedProducts(new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"}, "mouse"));
    }

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie root = new Trie();


        for (int i = 0; i < products.length; i++) {
            String word = products[i];

            Trie node = root;

            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);

                int index = c - 'a';

                if (node.next[index] == null) {
                    node.next[index] = new Trie();
                }

                node = node.next[index];

                if (node.queue.size() < 3) {
                    node.queue.add(word);
                } else if (word.compareTo(node.queue.peek()) < 0) {
                    node.queue.remove();
                    node.queue.add(word);
                }
            }

            node.isEnd = true;
        }

        Trie node = root;

        List<List<String>> ans = new ArrayList<>(searchWord.length());

        for (int i = 0; i < searchWord.length(); i++) {

            LinkedList<String> list = new LinkedList<>();

            char c = searchWord.charAt(i);

            int index = c - 'a';

            if (node.next[index] == null) {
                for (int j = i; j < searchWord.length(); j++) {
                    ans.add(Collections.emptyList());
                }
                break;
            }

            node = node.next[index];

            list.addAll(node.queue);

            list.sort(String::compareTo);

            ans.add(list);
        }

        return ans;
    }


    private static class Trie {
        Trie[] next = new Trie[26];
        boolean isEnd;
        PriorityQueue<String> queue = new PriorityQueue<>(3, Comparator.reverseOrder());
    }

}
