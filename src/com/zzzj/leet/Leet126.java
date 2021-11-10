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

    private static List<List<String>> result;

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        result = new ArrayList<>();

        Set<String> words = new HashSet<>(wordList);

        Set<String> visited = new HashSet<>(wordList.size());

        LinkedList<String> queue = new LinkedList<>();

        Map<String, Set<String>> path = new TreeMap<>();

        queue.add(beginWord);

        visited.add(beginWord);

        boolean hasResult = false;

        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            level++;

            for (int k = 0; k < size; k++) {
                String last = queue.removeFirst();

                char[] chars = last.toCharArray();

                for (int j = 0; j < last.length(); j++) {

                    char c = last.charAt(j);

                    for (char i = 'a'; i <= 'z'; i++) {
                        if (i == c) {
                            continue;
                        }
                        chars[j] = i;

                        String newStr = String.valueOf(chars);


                        if (words.contains(newStr)) {

                            boolean contains = visited.contains(newStr);

                            if (contains && path.get(newStr) != null && path.get(newStr).contains(last)) {
                                continue;
                            }

                            Set<String> set = path.get(last);
                            if (set == null) {
                                set = new HashSet<>();
                                set.add(newStr);
                                path.put(last, set);
                            } else {
                                set.add(newStr);
                            }

                            if (!contains) {
                                if (!endWord.equals(newStr)) {
                                    visited.add(newStr);
                                } else {
                                    hasResult = true;
                                }

                                queue.add(newStr);
                            }
                        }

                    }
                    chars[j] = c;
                }
            }
            if (hasResult) {
                break;
            }
        }

        if (hasResult) {
            buildPath(beginWord, endWord, path, new LinkedList<>());
        }

        return result;
    }

    private static void buildPath(String beginWord, String endWord, Map<String, Set<String>> graph, List<String> path) {
        Set<String> set = graph.get(beginWord);
        path.add(beginWord);
        if (set != null) {
            set.forEach(s -> buildPath(s, endWord, graph, path));
        } else if (endWord.equals(beginWord)) {
            result.add(new ArrayList<>(path));
        }
        path.remove(beginWord);
    }

}
