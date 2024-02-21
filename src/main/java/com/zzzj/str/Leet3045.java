package com.zzzj.str;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2024-02-20 12:20
 */
public class Leet3045 {

    public static void main(String[] args) {

        System.out.println(countPrefixSuffixPairs(new String[]{"ccb", "cb", "a", "caccc", "cbbb", "abbc", "aaa"}));

        System.out.println(countPrefixSuffixPairs(new String[]{"ab", "ab"}));

        System.out.println(countPrefixSuffixPairs(new String[]{"pa", "papa"}));

        System.out.println(countPrefixSuffixPairs(LeetUtils.convertString1("[\"a\",\"aba\",\"ababa\",\"aa\"]")));

    }

    public static long countPrefixSuffixPairs(String[] words) {

        int M = (int) (1e5 + 10);

        int[] z = new int[M];

        Trie root = new Trie();

        long ans = 0;

        for (String word : words) {

            zFunc(word, z);

            int N = word.length();

            Trie node = root;

            for (int i = 0; i < N; i++) {

                int index = word.charAt(i) - 'a';

                if (node.next[index] == null)
                    node.next[index] = new Trie();

                node = node.next[index];

                if (z[N - i - 1] == i + 1) {
                    ans += node.cnt;
                }

            }

            node.cnt++;
        }

        return ans;
    }

    private static class Trie {
        Trie[] next = new Trie[26];
        int cnt;
    }

    public static void append(String word, Trie root) {

        Trie node = root;

        for (int i = 0; i < word.length(); i++) {

            int index = word.charAt(i) - 'a';

            if (node.next[index] == null)
                node.next[index] = new Trie();

            node = node.next[index];
        }

        node.cnt++;
    }

    public static void zFunc(String word, int[] z) {

        int N = word.length();

        z[0] = N;

        int l = 0, r = 0;

        for (int k = 1; k < N; k++) {

            if (k <= r) {
                int prev = k - l;
                if (z[prev] + k < r) {
                    z[k] = z[prev];
                } else {
                    l = k;
                    while (r < N && word.charAt(r) == word.charAt(r - l)) {
                        r++;
                    }
                    z[k] = r - l;
                    r--;
                }
            } else {
                l = k;
                r = k;
                while (r < N && word.charAt(r) == word.charAt(r - l)) {
                    r++;
                }
                z[k] = r - l;
                r--;
            }
        }

    }

}
