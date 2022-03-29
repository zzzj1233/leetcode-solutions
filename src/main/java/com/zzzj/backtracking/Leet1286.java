package com.zzzj.backtracking;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-03-25 16:01
 */
public class Leet1286 {

    public static void main(String[] args) {
        CombinationIterator abc = new CombinationIterator("abcdef", 2);

    }

    private static class CombinationIterator {

        public LinkedList<String> queue;

        public CombinationIterator(String characters, int combinationLength) {
            queue = new LinkedList<>();

            char[] path = new char[combinationLength];

            for (int i = 0; i < characters.length() - combinationLength + 1; i++) {
                dfs(characters.toCharArray(), combinationLength, path, i, 0);
            }
        }

        private void dfs(char[] chars, int len, char[] path, int index, int pathIndex) {
            if (pathIndex == path.length) {
                queue.add(String.valueOf(path));
                return;
            }

            path[pathIndex] = chars[index];

            if (pathIndex + 1 == path.length) {
                queue.add(String.valueOf(path));
                return;
            }

            for (int i = index + 1; i < chars.length; i++) {
                dfs(chars, len, path, i, pathIndex + 1);
            }
        }

        public String next() {
            return queue.removeFirst();
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }

    }


}
