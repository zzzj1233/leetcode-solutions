package com.zzzj.backtracking;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-02-17 17:49
 */
public class Leet1258 {

    public static void main(String[] args) {
        System.out.println(
                generateSentences(
                        LeetUtils.convertListStrings("[[\"happy\",\"joy\"],[\"sad\",\"sorrow\"],[\"joy\",\"cheerful\"]]"),
                        "I am happy today but was sad yesterday"
                )
        );
    }

    public static List<String> generateSentences(List<List<String>> synonyms, String text) {

        Map<String, Set<String>> graph = new HashMap<>();

        for (List<String> synonym : synonyms) {
            String w1 = synonym.get(0);
            String w2 = synonym.get(1);

            graph.computeIfAbsent(w1, s -> new HashSet<>()).add(w2);
            graph.computeIfAbsent(w2, s -> new HashSet<>()).add(w1);
        }

        String[] words = text.split(" ");

        ArrayList<String> ans = new ArrayList<>();

        dfs(ans, words, null, 0, graph, new Set[words.length], new LinkedList<>());

        ans.sort(String::compareTo);

        return ans;
    }

    public static void dfs(List<String> result,
                           String[] words,
                           String word,
                           int index,
                           Map<String, Set<String>> graph,
                           Set[] visited,
                           LinkedList<String> path
    ) {
        if (index >= words.length) {
            result.add(String.join(" ", path));
            return;
        }

        if (word == null) {
            word = words[index];
        }

        Set<String> neigh = graph.get(word);

        if (neigh != null) {
            if (visited[index] == null) {
                visited[index] = new HashSet<String>();
            }

            visited[index].add(word);

            for (String s : neigh) {
                if (visited[index].contains(s)) {
                    continue;
                }
                dfs(result, words, s, index, graph, visited, path);
            }
            visited[index].remove(word);
        }

        path.add(word);
        dfs(result, words, null, index + 1, graph, visited, path);
        path.removeLast();
    }


}
