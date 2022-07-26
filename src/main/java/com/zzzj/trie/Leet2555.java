package com.zzzj.trie;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-07-26 10:29
 */
public class Leet2555 {


    // ["MapSum", "insert", "sum", "insert", "insert", "sum"]
    // [[], ["apple",3], ["ap"], ["app",2], ["apple", 2], ["ap"]]

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        System.out.println(LeetUtils.executeExpression("[\"insert\", \"sum\", \"insert\", \"insert\", \"sum\"]", "[[\"apple\",3], [\"ap\"], [\"app\",2], [\"apple\", 2], [\"ap\"]]", mapSum));
    }

    private static class MapSum {

        Trie root = new Trie();

        public MapSum() {

        }

        public void insert(String key, int val) {
            Trie node = this.root;

            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                int index = c - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new Trie();
                }
                node = node.next[index];
            }

            int newValue;

            if (node.end) {
                newValue = val - node.selfValue;
            } else {
                newValue = val;
            }

            node.end = true;

            node = root;

            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                int index = c - 'a';
                node = node.next[index];
                node.value += newValue;
            }
            node.selfValue = val;
        }

        public int sum(String prefix) {
            int sum = 0;

            Trie node = this.root;

            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                int index = c - 'a';
                if (node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }

            return node.value;
        }

        private static class Trie {
            Trie[] next = new Trie[26];
            int value;
            int selfValue;
            boolean end;
        }

    }


}
