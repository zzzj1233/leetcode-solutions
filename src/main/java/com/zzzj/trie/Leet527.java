package com.zzzj.trie;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-07-25 15:54
 */
public class Leet527 {

    public static void main(String[] args) {

        Solution solution = new Solution();

        for (int i = 0; i < 1000; i++) {
            int N = LeetUtils.random.nextInt(100) + 1;
            List<String> words = new ArrayList<>(N);
            for (int j = 0; j < N; j++) {
                words.add(LeetUtils.randomString(LeetUtils.random.nextInt(100) + 2, false));
            }

            List<String> myAns = wordsAbbreviation(words);

            if (!myAns.equals(solution.wordsAbbreviation(words))) {
                System.out.println("Error");
                System.out.println(LeetUtils.stringsToLeetCode(words));
                System.out.println(myAns);
                System.out.println(solution.wordsAbbreviation(words));
                return;
            }
        }
        System.out.println("Ok");
    }


    // 正如 方法 2 中所述，我们将单词按照长度、首字母、最后一个字母分组后，我们讨论何时一个组内的单词没有最长公共前缀。
    //
    // 将一个组内的单词放到 trie 树中（前缀树），然后我们在每一个节点（代表某个前缀 P）统计有多少个单词的前缀为 P。如果数目为 1 ，我们就知道这个前缀是唯一的。

    public static List<String> wordsAbbreviation(List<String> words) {
        int N = words.size();

        Trie root = new Trie();

        for (int i = 0; i < N; i++) {
            Trie node = root;

            String word = words.get(i);

            final int wordLen = word.length();

            int lastCharIndex = word.charAt(wordLen - 1) - 'a';

            for (int j = 0; j < wordLen - 2; j++) {
                char c = word.charAt(j);

                int charIndex = c - 'a';

                if (node.next[charIndex] == null) {
                    node.next[charIndex] = new Trie();
                }

                node = node.next[charIndex];

                Map<Integer, Integer> innerMap = node.lastCharCount.computeIfAbsent(lastCharIndex, k -> new HashMap<>());

                Integer oldCount = innerMap.getOrDefault(wordLen, 0);

                innerMap.put(wordLen, oldCount + 1);
            }

        }

        List<String> ans = new ArrayList<>(N);

        OUTER:
        for (int i = 0; i < N; i++) {
            String word = words.get(i);

            int wordLen = word.length();

            StringBuilder ansBuilder = new StringBuilder(wordLen);

            ansBuilder.append(word.charAt(0));

            int firstCharIndex = word.charAt(0) - 'a';

            char lastChar = word.charAt(wordLen - 1);

            Trie node = root.next[firstCharIndex];

            for (int j = 1; j < wordLen - 2; j++) {
                // 尝试缩写
                if (node.lastCharCount.get(lastChar - 'a').get(wordLen) > 1) {
                    // 不可以
                    ansBuilder.append(word.charAt(j));
                    node = node.next[word.charAt(j) - 'a'];
                } else {
                    ansBuilder.append(wordLen - j - 1);
                    ansBuilder.append(lastChar);
                    ans.add(ansBuilder.toString());
                    continue OUTER;
                }
            }

            if (wordLen > 2) {
                ansBuilder.append(word.charAt(wordLen - 2));
            }

            ansBuilder.append(lastChar);
            ans.add(ansBuilder.toString());
        }


        return ans;
    }


    private static class Trie {
        Trie[] next = new Trie[26];
        Map<Integer, Map<Integer, Integer>> lastCharCount = new HashMap<>(4);
    }


    private static class Solution {
        public List<String> wordsAbbreviation(List<String> words) {
            Map<String, List<IndexedWord>> groups = new HashMap();
            String[] ans = new String[words.size()];

            int index = 0;
            for (String word : words) {
                String ab = abbrev(word, 0);
                if (!groups.containsKey(ab))
                    groups.put(ab, new ArrayList());
                groups.get(ab).add(new IndexedWord(word, index));
                index++;
            }

            for (List<IndexedWord> group : groups.values()) {
                TrieNode trie = new TrieNode();
                for (IndexedWord iw : group) {
                    TrieNode cur = trie;
                    for (char letter : iw.word.substring(1).toCharArray()) {
                        if (cur.children[letter - 'a'] == null)
                            cur.children[letter - 'a'] = new TrieNode();
                        cur.count++;
                        cur = cur.children[letter - 'a'];
                    }
                }

                for (IndexedWord iw : group) {
                    TrieNode cur = trie;
                    int i = 1;
                    for (char letter : iw.word.substring(1).toCharArray()) {
                        if (cur.count == 1) break;
                        cur = cur.children[letter - 'a'];
                        i++;
                    }
                    ans[iw.index] = abbrev(iw.word, i - 1);
                }
            }

            return Arrays.asList(ans);
        }

        public String abbrev(String word, int i) {
            int N = word.length();
            if (N - i <= 3) return word;
            return word.substring(0, i + 1) + (N - i - 2) + word.charAt(N - 1);
        }

    }

    private static class TrieNode {
        TrieNode[] children;
        int count;

        TrieNode() {
            children = new TrieNode[26];
            count = 0;
        }
    }

    private static class IndexedWord {
        String word;
        int index;

        IndexedWord(String w, int i) {
            word = w;
            index = i;
        }
    }


}
