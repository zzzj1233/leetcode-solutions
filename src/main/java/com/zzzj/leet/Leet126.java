package com.zzzj.leet;

import java.util.*;

/**
 * @author zzzj
 * @create 2021-10-21 16:07
 */
public class Leet126 {

    public static void main(String[] args) {
        System.out.println(findLadders("hit", "cog", TreeNode.buildList("[\"hot\",\"dot\",\"dog\",\"lot\",\"log\",\"cog\"]")));
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        // bfs
        LinkedList<String> queue = new LinkedList<>();

        queue.add(endWord);

        Map<String, Set<String>> path = new HashMap<>();

        while (!queue.isEmpty()) {

            int size = queue.size();

            boolean find = false;

            for (int i = 0; i < size; i++) {

                String word = queue.removeFirst();

                if (word.equals(beginWord)) {
                    find = true;
                }

                for (int j = 0; j < word.length(); j++) {

                    char c = word.charAt(j);

                    
                }


            }

            if (find)
                break;
        }

        return null;
    }

}
