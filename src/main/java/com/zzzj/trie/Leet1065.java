package com.zzzj.trie;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-07-27 14:27
 */
public class Leet1065 {

    /**
     * 给出字符串 text 和字符串列表 words, 返回所有的索引对 [i, j] 使得在索引对范围内的子字符串 text[i]...text[j]（包括i和j）属于字符串列表 words。
     * <p>
     *
     * <p>
     * 示例 1:
     * <p>
     * 输入: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
     * 输出: [[3,7],[9,13],[10,17]]
     * 示例 2:
     * <p>
     * 输入: text = "ababa", words = ["aba","ab"]
     * 输出: [[0,1],[0,2],[2,3],[2,4]]
     * 解释:
     * 注意，返回的配对可以有交叉，比如，"aba" 既在 [0,2] 中也在 [2,4] 中
     * 按序返回索引对 [i,j]（即，按照索引对的第一个索引进行排序，当第一个索引对相同时按照第二个索引对排序）
     */


    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(indexPairs("thestoryofleetcodeandme", new String[]{"story", "fleet", "leetcode"})));

        System.out.println(Arrays.deepToString(indexPairs("ababa", new String[]{"aba", "ab"})));
    }

    public static int[][] indexPairs(String text, String[] words) {
        List<int[]> list = new ArrayList<>(words.length);

        Trie root = new Trie();

        // 1. 构建Trie树
        for (int i = 0; i < words.length; i++) {
            Trie node = root;

            String word = words[i];

            int wordLen = word.length();

            for (int j = 0; j < wordLen; j++) {
                int index = word.charAt(j) - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new Trie();
                }
                node = node.next[index];
            }
            node.end = true;
        }

        LinkedList<Node> nodes = new LinkedList<>();

        for (int i = 0; i < text.length(); i++) {
            int index = text.charAt(i) - 'a';

            Iterator<Node> iterator = nodes.iterator();

            LinkedList<Node> addition = new LinkedList<>();

            while (iterator.hasNext()) {
                Node next = iterator.next();
                iterator.remove();
                if (next.trie.next[index] == null) {
                    continue;
                }
                if (next.trie.next[index].end) {
                    list.add(new int[]{next.start, i});
                }
                addition.add(new Node(next.trie.next[index], next.start));
            }

            Trie node = root.next[index];

            nodes = addition;

            if (node != null) {
                if (node.end) {
                    list.add(new int[]{i, i});
                }
                nodes.add(new Node(node, i));
            }
        }

        int[][] ans = new int[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        Arrays.sort(ans, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        return ans;
    }

    private static class Trie {
        Trie[] next = new Trie[26];
        boolean end;
    }

    private static class Node {
        Trie trie;
        int start;

        public Node(Trie trie, int start) {
            this.trie = trie;
            this.start = start;
        }
    }


}
