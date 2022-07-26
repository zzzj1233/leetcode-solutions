package com.zzzj.trie;


import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-07-26 17:49
 */
public class Leet1166 {


    private static class FileSystem {

        private final Trie root;

        public FileSystem() {
            this.root = new Trie();
        }

        public boolean createPath(String path, int value) {
            int level = 1;

            Trie node = root;

            int last = path.lastIndexOf('/');
            int first = path.indexOf('/');

            if (last != first && get(path, 1, last) == -1) {
                return false;
            }

            for (int i = 1; i < path.length(); i++) {
                char c = path.charAt(i);
                if (c == '/') {
                    level++;
                    continue;
                }
                int index = c - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new Trie();
                }
                node = node.next[index];
            }

            if (node.level == null) {
                node.level = new HashMap<>(4);
            }
            if (node.level.containsKey(level)) {
                return false;
            }

            node.level.put(level, value);

            return true;
        }

        public int get(String path) {
            return get(path, 1, path.length());
        }

        private int get(String path, int i, int j) {
            int level = 1;

            Trie node = root;

            for (int k = i; k < j; k++) {
                char c = path.charAt(k);
                if (c == '/') {
                    level++;
                    continue;
                }
                int index = c - 'a';
                if (node.next[index] == null) {
                    return -1;
                }
                node = node.next[index];
            }

            if (node.level == null) {
                return -1;
            }

            Integer ans = node.level.get(level);

            return ans == null ? -1 : ans;
        }

        private static class Trie {
            Trie[] next = new Trie[26];
            Map<Integer, Integer> level;
        }

    }


}
