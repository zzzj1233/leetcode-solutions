package com.zzzj.trie;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-07-21 17:46
 */
public class Leet588 {


    private static class FileSystem {

        private final Trie trie;

        private static class Trie {
            Trie[] next = new Trie[26];
            boolean isEnd;
        }

        private Map<String, String> contentMap = new HashMap<>(8);

        public FileSystem() {
            this.trie = new Trie();
        }

        public List<String> ls(String path) {
            List<String> ret = new LinkedList<>();

            Trie node = trie;

            for (int i = 0; i < path.length(); i++) {
                char c = path.charAt(i);
                if (node.next[c] == null) {
                    return Collections.emptyList();
                }
                node = node.next[c];
            }

            getResult(node, ret, new StringBuilder().append(path));

            return ret;
        }


        private void getResult(Trie node, List<String> list, StringBuilder builder) {

            int length = builder.length();

            for (int i = 0; i < node.next.length; i++) {
                if (node.next[i] != null) {
                    Trie newNode = node.next[i];
                    builder.append(((char) i));
                    getResult(newNode, list, builder);
                    builder.setLength(length);
                }
            }

            if (node.isEnd) {
                list.add(builder.toString());
            }

        }

        public void mkdir(String path) {

            Trie node = trie;

            for (int i = 0; i < path.length(); i++) {
                char c = path.charAt(i);
                if (node.next[c] == null) {
                    node.next[c] = new Trie();
                }
                node = node.next[c];
            }

            node.isEnd = true;
        }

        public void addContentToFile(String filePath, String content) {
            if (!contentMap.containsKey(filePath)) {
                mkdir(filePath);
                contentMap.put(filePath, content);
            } else {
                contentMap.put(filePath, contentMap.get(filePath) + content);
            }
        }

        public String readContentFromFile(String filePath) {
            return contentMap.get(filePath);
        }

    }

}
