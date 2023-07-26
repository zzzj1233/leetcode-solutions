package com.zzzj.contest.week354;

import java.util.List;

public class Leet6924 {

    public static int longestValidSubstring(String word, List<String> forbidden) {

        Trie trie = buildTrie(forbidden);

        int N = word.length();

        int left = 0;

        int right = N - 1;

        int ans = 0;

        while (left < N) {

            // 看看最长能到哪
            right = Math.min(right, search(trie, word, left, right));


            left++;
        }

        return -1;
    }

    static int search(Trie trie, String word, int left, int right) {

        Trie cur = trie;

        for (int i = left; i <= right; i++) {

            int index = word.charAt(i) - 'a';

            if (cur.next[index] == null) {
                cur = trie;
                continue;
            }

            cur = cur.next[index];

            if (cur.end) return i - 1;
        }

        return right;
    }

    static Trie buildTrie(List<String> words) {

        Trie root = new Trie();

        for (String word : words) {

            Trie cur = root;

            for (int i = 0; i < word.length(); i++) {

                char c = word.charAt(i);

                int index = c - 'a';

                if (cur.next[index] == null) cur.next[index] = new Trie();

                cur = cur.next[index];

            }

            cur.end = true;

        }

        return root;
    }

    static class Trie {

        boolean end;

        Trie[] next = new Trie[26];

    }

}
