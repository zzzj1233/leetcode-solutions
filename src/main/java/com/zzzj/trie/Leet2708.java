package com.zzzj.trie;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-07-26 10:45
 */
public class Leet2708 {

    public static void main(String[] args) {
//        System.out.println(Arrays.deepToString(multiSearch("mississippi", new String[]{"is", "ppi", "hi", "sis", "i", "ssippi"})));

        Solution solution = new Solution();

        for (int i = 0; i < 10000; i++) {
            String big = LeetUtils.randomString(100, false);
            int N = LeetUtils.random.nextInt(100) + 1;
            String[] smalls = new String[N];
            for (int j = 0; j < N; j++) {
                smalls[j] = LeetUtils.randomString(20, false);
            }
            if (!Arrays.deepEquals(multiSearch(big, smalls), solution.multiSearch(big, smalls))) {
                System.out.println("Error");
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int[][] multiSearch(String big, String[] smalls) {

        Trie root = new Trie();

        for (int i = 0; i < smalls.length; i++) {
            String word = smalls[i];

            Trie node = root;

            for (int j = 0; j < word.length(); j++) {
                int index = word.charAt(j) - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new Trie();
                }
                node = node.next[index];
            }

            node.end = true;
            node.index = i;
            node.len = word.length();
        }

        LinkedList<Trie> list = new LinkedList<>();

        List<List<Integer>> ansList = new ArrayList<>(smalls.length);

        for (int i = 0; i < smalls.length; i++) {
            ansList.add(new ArrayList<>(0));
        }

        for (int i = 0; i < big.length(); i++) {
            int index = big.charAt(i) - 'a';

            Iterator<Trie> iterator = list.iterator();

            LinkedList<Trie> addList = new LinkedList<>();

            while (iterator.hasNext()) {
                Trie next = iterator.next();

                iterator.remove();

                Trie nextNext = next.next[index];

                if (nextNext != null) {
                    if (nextNext.end) {
                        ansList.get(nextNext.index).add(i - nextNext.len + 1);
                    }
                    addList.add(nextNext);
                }
            }

            list.addAll(addList);

            if (root.next[index] != null) {
                list.add(root.next[index]);
                if (root.next[index].end) {
                    ansList.get(root.next[index].index).add(i);
                }
            }
        }

        int[][] ans = new int[smalls.length][];

        for (int i = 0; i < smalls.length; i++) {
            List<Integer> items = ansList.get(i);
            ans[i] = new int[items.size()];
            for (int j = 0; j < items.size(); j++) {
                ans[i][j] = items.get(j);
            }
        }

        return ans;
    }

    private static class Trie {
        Trie[] next = new Trie[26];
        int index;
        int len;
        boolean end;
    }

    static class Solution {

        static class Trie {
            TrieNode root;

            public Trie(String[] words) {
                root = new TrieNode();
                for (String word : words) {
                    TrieNode node = root;
                    for (char w : word.toCharArray()) {
                        int i = w - 'a';
                        if (node.next[i] == null) {
                            node.next[i] = new TrieNode();
                        }
                        node = node.next[i];
                    }
                    node.end = word;
                }
            }

            public List<String> search(String str) {
                TrieNode node = root;
                List<String> res = new ArrayList<>();
                for (char c : str.toCharArray()) {
                    int i = c - 'a';
                    if (node.next[i] == null) {
                        break;
                    }
                    node = node.next[i];
                    if (node.end != null) {
                        res.add(node.end);
                    }
                }
                return res;
            }
        }

        static class TrieNode {
            String end;
            TrieNode[] next = new TrieNode[26];
        }


        public int[][] multiSearch(String big, String[] smalls) {
            Trie trie = new Trie(smalls);
            Map<String, List<Integer>> hit = new HashMap<>();
            for (int i = 0; i < big.length(); i++) {
                List<String> matchs = trie.search(big.substring(i));
                for (String word : matchs) {
                    if (!hit.containsKey(word)) {
                        hit.put(word, new ArrayList<>());
                    }
                    hit.get(word).add(i);
                }
            }

            int[][] res = new int[smalls.length][];
            for (int i = 0; i < smalls.length; i++) {
                List<Integer> list = hit.get(smalls[i]);
                if (list == null) {
                    res[i] = new int[0];
                    continue;
                }
                int size = list.size();
                res[i] = new int[size];
                for (int j = 0; j < size; j++) {
                    res[i][j] = list.get(j);
                }
            }
            return res;
        }
    }

}
