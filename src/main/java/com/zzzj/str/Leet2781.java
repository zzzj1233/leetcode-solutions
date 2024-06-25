package com.zzzj.str;

import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2024-06-21 15:14
 */
public class Leet2781 {

    public static void main(String[] args) {

        System.out.println(longestValidSubstring("a", Arrays.asList("a")));

        System.out.println(longestValidSubstring("cbaaaabc", Arrays.asList("aaa", "cb")));

        System.out.println(longestValidSubstring("leetcode", Arrays.asList("de", "le", "e")));

    }

    public static int longestValidSubstring(String word, List<String> forbidden) {

        Trie root = buildTrie(forbidden);

        int N = word.length();

        int r = N - 1;

        int l = N - 1;

        int ans = 0;

        while (l >= 0) {

            int c;

            while ((c = check(word, l, r, root)) >= 0 && r >= l) {
                r = c - 1;
            }

            ans = Math.max(ans, r - l + 1);

            l--;
        }

        return ans;
    }

    public static int check(String word, int l, int r, Trie root) {

        Trie node = root;

        int end = Math.min(
                l + 9,
                r
        );

        while (l <= end && !node.end) {

            int index = word.charAt(l) - 'a';

            if (node.next[index] == null)
                return -1;

            node = node.next[index];

            if (node.end)
                return l;

            l++;
        }

        return -1;
    }

    public static Trie buildTrie(List<String> words) {

        Trie root = new Trie();

        for (String word : words) {

            int N = word.length();

            Trie node = root;

            for (int i = 0; i < N; i++) {

                int index = word.charAt(i) - 'a';

                if (node.next[index] == null)
                    node.next[index] = new Trie();
                node = node.next[index];
            }

            node.end = true;
        }

        return root;
    }

    private static class Trie {
        Trie[] next = new Trie[26];
        boolean end;
    }

}
