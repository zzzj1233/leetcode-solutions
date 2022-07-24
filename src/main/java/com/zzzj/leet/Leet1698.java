package com.zzzj.leet;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Zzzj
 * @create 2022-07-24 16:54
 */
public class Leet1698 {

    public static void main(String[] args) {
        System.out.println(countDistinct("aabbaba"));
        System.out.println(countDistinct("abcdefg"));
    }

    public static int countDistinct(String s) {

        int N = s.length();

        Trie root = new Trie();

        int ans = 0;

        for (int i = 0; i < N; i++) {

            Trie node = root;

            for (int j = i; j < N; j++) {

                char c = s.charAt(j);

                int index = c - 'a';

                if (node.next[index] == null) {
                    node.next[index] = new Trie();
                    ans++;
                }

                node = node.next[index];
            }

        }

        return ans;
    }

    private static class Trie {
        Trie[] next = new Trie[26];
    }

}
