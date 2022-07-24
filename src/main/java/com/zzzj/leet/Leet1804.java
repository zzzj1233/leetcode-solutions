package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-07-23 23:02
 */
public class Leet1804 {

    private static class Trie {

        private final Inner root;

        private static class Inner {
            Inner[] next = new Inner[26];
            int count;
        }

        public Trie() {
            this.root = new Inner();
        }

        public void insert(String word) {

            Inner node = root;

            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);

                int index = c - 'a';

                if (node.next[index] == null) {
                    node.next[index] = new Inner();
                }

                node = node.next[index];
            }

            node.count++;
        }

        private Inner search(String word) {
            Inner node = root;

            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);

                int index = c - 'a';

                if (node.next[index] == null) {
                    return null;
                }

                node = node.next[index];
            }

            return node;
        }

        public int countWordsEqualTo(String word) {
            Inner search = search(word);
            return search == null ? 0 : search.count;
        }

        public int countWordsStartingWith(String prefix) {
            Inner search = search(prefix);
            if (search == null) {
                return 0;
            }
            return dfs(search);
        }

        public int dfs(Inner node) {
            int result = node.count;
            for (int i = 0; i < node.next.length; i++) {
                if (node.next[i] != null) {
                    result += dfs(node.next[i]);
                }
            }
            return result;
        }

        public void erase(String word) {
            Inner search = search(word);

            if (search != null && search.count > 0) {
                search.count--;
            }

        }

    }

}
