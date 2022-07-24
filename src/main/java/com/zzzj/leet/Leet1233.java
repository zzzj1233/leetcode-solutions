package com.zzzj.leet;

import java.util.*;

/**
 * @author Zzzj
 * @create 2022-07-24 15:12
 */
public class Leet1233 {

    public static void main(String[] args) {
        System.out.println(removeSubfolders(new String[]{"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"}));
    }

    public static List<String> removeSubfolders(String[] folder) {
        List<String> ans = new LinkedList<>();

        Trie root = new Trie();

        Arrays.sort(folder, Comparator.comparingInt(String::length));

        OUTER:
        for (int i = 0; i < folder.length; i++) {
            String word = folder[i];

            int level = 0;

            Trie node = root;

            for (int j = 1; j < word.length(); j++) {

                char c = word.charAt(j);

                if (c == '/') {
                    if (node.level.contains(level)){
                        continue OUTER;
                    }
                    level++;
                    continue;
                }

                int index = c - 'a';

                if (node.next[index] == null) {
                    node.next[index] = new Trie();
                }
                node = node.next[index];
            }

            if (!node.level.contains(level)) {
                node.level.add(level);
                ans.add(word);
            }

        }

        return ans;
    }

    private static class Trie {
        Trie[] next = new Trie[26];
        Set<Integer> level = new HashSet<>(4);
    }

}
