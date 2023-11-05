package com.zzzj.str;

/**
 * @author zzzj
 * @create 2023-10-20 10:52
 */
public class Leet2416 {

    public static int[] sumPrefixScores(String[] words) {

        Trie root = new Trie();

        for (String word : words) {

            Trie cur = root;

            for (int i = 0; i < word.length(); i++) {

                char c = word.charAt(i);

                int index = c - 'a';

                if (cur.next[index] == null)
                    cur.next[index] = new Trie();
                cur = cur.next[index];
                cur.cnt++;
            }

        }

        int N = words.length;

        int[] ans = new int[N];

        for (int i = 0; i < N; i++)
            ans[i] = search(root, words[i]);

        return ans;
    }

    public static int search(Trie trie, String word) {

        int ans = 0;

        Trie cur = trie;

        for (int i = 0; i < word.length(); i++) {

            int index = word.charAt(i) - 'a';

            cur = cur.next[index];

            ans += cur.cnt;
        }

        return ans;
    }

    public static class Trie {
        Trie[] next;
        int cnt;

        public Trie() {
            this.next = new Trie[26];
        }

    }

}
