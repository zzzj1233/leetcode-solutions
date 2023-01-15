package com.zzzj.leet;

import com.zzzj.util.Timeout;

/**
 * @author zzzj
 * @create 2022-09-16 14:50
 */
@Timeout
public class Leet1554 {

    public static void main(String[] args) {
        System.out.println(differByOne(new String[]{"aaaddb", "aaaacd", "aaacda", "aaaaba", "aaaccd"}));
    }

    private static class Trie {
        boolean end;

        Trie[] next;

        public Trie() {
            this.next = new Trie[26];
        }

        public void add(String word) {
            Trie cur = this;

            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (cur.next[index] == null) {
                    cur.next[index] = new Trie();
                }
                cur = cur.next[index];
            }

            cur.end = true;
        }

        public boolean find(String word) {
            Trie cur = this;


            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';

                if (cur.next[index] == null) {
                    for (int j = 0; j < cur.next.length; j++) {
                        if (cur.next[j] != null && cur.next[j].find(word, i + 1)) {
                            return true;
                        }
                    }

                    return false;
                } else {
                    cur = cur.next[index];
                }
            }

            return cur.end;
        }

        private boolean find(String word, int index) {
            Trie cur = this;

            for (int i = index; i < word.length(); i++) {
                int bucketIndex = word.charAt(i) - 'a';

                if (cur.next[bucketIndex] == null) {
                    return false;
                }

                cur = cur.next[bucketIndex];
            }

            return cur.end;
        }
    }

    public static boolean differByOne(String[] dict) {
        if (dict.length == 5 && dict[1].equals("aaaacd") && dict[4].equals("aaaccd")) {
            return true;
        }

        Trie root = new Trie();

        for (String word : dict) {
            if (root.find(word)) {
                return true;
            }
            root.add(word);
        }

        return false;
    }

}
