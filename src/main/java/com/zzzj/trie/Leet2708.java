package com.zzzj.trie;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-07-26 10:45
 */
public class Leet2708 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(multiSearch("mississippi", new String[]{"is", "ppi", "hi", "sis", "i", "ssippi"})));

//        System.exit(0);

        Solution solution = new Solution();

        for (int i = 0; i < 10000; i++) {
            String big = LeetUtils.randomString(10, false);
            int N = LeetUtils.random.nextInt(100) + 1;
            String[] smalls = new String[N];
            for (int j = 0; j < N; j++) {
                smalls[j] = LeetUtils.randomString(5, false);
            }
            if (!Arrays.deepEquals(multiSearch(big, smalls), solution.multiSearch(big, smalls))) {
                System.out.println("Error");
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int[][] multiSearch(String big, String[] smalls) {

        int N = big.length();

        int M = smalls.length;

        List<List<Integer>> ans = new ArrayList<>(M);

        for (int i = 0; i < M; i++) {

            List<Integer> list = new ArrayList<>();

            ans.add(list);

            String search = smalls[i];

            if (search.isEmpty() || search.length() > N)
                continue;

            int[] next = next(search);

            int x = 0;
            int y = 0;

            int K = search.length();

            while (x < N) {
                if (big.charAt(x) == search.charAt(y)) {
                    x++;
                    y++;
                    if (y == K) {
                        list.add(x - K);
                        y = Math.max(0, next[y]);
                    }
                } else if (next[y] >= 0) {
                    y = next[y];
                } else {
                    x++;
                }

            }

        }


        return ans.stream()
                .map(inner -> inner.stream().mapToInt(value -> value).toArray())
                .toArray(int[][]::new);
    }

    public static int[] next(String str) {

        if (str.length() == 1)
            return new int[]{-1, 0};

        int N = str.length();

        int[] next = new int[N + 1];

        next[0] = -1;
        next[1] = 0;

        int index = 2;
        int cc = 0;

        while (index <= N) {
            if (str.charAt(index - 1) == str.charAt(cc)) {
                next[index++] = ++cc;
            } else if (next[cc] >= 0) {
                cc = next[cc];
            } else {
                index++;
            }
        }

        return next;
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
