package com.zzzj.trie;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-07-26 14:19
 */
public class Leet2704 {

    public static void main(String[] args) {

//        System.out.println(respace(new String[]{"looked", "just", "like", "her", "brother"}, "jesslookedjustliketimherbrother"));
//
//        System.exit(0);

        Solution solution = new Solution();

        for (; ; ) {
            int N = LeetUtils.random.nextInt(5) + 1;
            String[] words = new String[N];
            for (int j = 0; j < N; j++) {
                words[j] = LeetUtils.randomString(LeetUtils.random.nextInt(5) + 1, false);
            }
            String sentence = LeetUtils.randomString(LeetUtils.random.nextInt(5) + 10, false);

            if (respace(words, sentence) != solution.respace(words, sentence)) {
                System.out.println("Error");
                System.out.println(LeetUtils.stringsToLeetCode(words));
                System.out.println(sentence);
                System.out.println("myAns = " + respace(words, sentence));
                System.out.println("ans   = " + solution.respace(words, sentence));
                break;
            }
        }
        System.out.println("Ok");
    }

    public static int respace(String[] dictionary, String sentence) {
        List<String>[] lists = new List[26];

        for (int i = 0; i < 26; i++) {
            lists[i] = new ArrayList<>();
        }

        for (String s : dictionary) {
            int index = s.charAt(0) - 'a';
            lists[index].add(s);
        }

        int N = sentence.length();

        int[] dp = new int[N + 1];

        for (int i = N - 1; i >= 0; i--) {
            int index = sentence.charAt(i) - 'a';

            List<String> words = lists[index];

            if (words.isEmpty()) {
                dp[i] = 1 + dp[i + 1];
            } else {
                int min = Integer.MAX_VALUE;
                for (String word : words) {
                    if (equals(sentence, i, word)) {
                        min = Math.min(min, dp[i + word.length()]);
                    }
                }
                dp[i] = Math.min(min, dp[i + 1] + 1);
            }
        }

        return dp[0];
    }


    public static boolean equals(String sentence, int index, String word) {
        int wordLen = word.length();
        int remainLen = sentence.length() - index;

        if (remainLen < wordLen) {
            return false;
        }

        for (int i = 0; i < wordLen; i++) {
            if (word.charAt(i) != sentence.charAt(index + i)) {
                return false;
            }
        }

        return true;
    }


    private static class Solution {
        public int respace(String[] dictionary, String sentence) {
            int n = sentence.length();

            Trie root = new Trie();
            for (String word : dictionary) {
                root.insert(word);
            }

            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            for (int i = 1; i <= n; ++i) {
                dp[i] = dp[i - 1] + 1;

                Trie curPos = root;
                for (int j = i; j >= 1; --j) {
                    int t = sentence.charAt(j - 1) - 'a';
                    if (curPos.next[t] == null) {
                        break;
                    } else if (curPos.next[t].isEnd) {
                        dp[i] = Math.min(dp[i], dp[j - 1]);
                    }
                    if (dp[i] == 0) {
                        break;
                    }
                    curPos = curPos.next[t];
                }
            }
            return dp[n];
        }
    }

    private static class Trie {
        public Trie[] next;
        public boolean isEnd;

        public Trie() {
            next = new Trie[26];
            isEnd = false;
        }

        public void insert(String s) {
            Trie curPos = this;

            for (int i = s.length() - 1; i >= 0; --i) {
                int t = s.charAt(i) - 'a';
                if (curPos.next[t] == null) {
                    curPos.next[t] = new Trie();
                }
                curPos = curPos.next[t];
            }
            curPos.isEnd = true;
        }
    }

}
