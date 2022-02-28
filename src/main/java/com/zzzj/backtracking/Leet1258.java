package com.zzzj.backtracking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-02-17 17:49
 */
public class Leet1258 {

    public static void main(String[] args) {
//        List<String> list = generateSentences(Arrays.asList(Arrays.asList("happy", "joy"), Arrays.asList("sad", "sorrow"), Arrays.asList("joy", "cheerful")), "I am happy today but was sad yesterday");

        List<String> list = generateSentences(Arrays.asList(Arrays.asList("v", "yr")), "v v v v yZ");

        System.out.println(String.join("\n", list));
    }

    public static List<String> generateSentences(List<List<String>> synonyms, String text) {
        List<String> ans = new ArrayList<>();

        Map<String, Set<String>> map = new HashMap<>(synonyms.size());

        for (List<String> synonym : synonyms) {

            for (String s : synonym) {
                map.computeIfAbsent(s, s1 -> new HashSet<>()).addAll(synonym);
            }

        }

        process(ans, map, new HashSet<>(), new LinkedList<>(), text.split(" "), 0);

        return ans.stream().sorted().collect(Collectors.toList());
    }

    public static void process(List<String> ans,
                               Map<String, Set<String>> map, Set<String> used,
                               LinkedList<String> path,
                               String[] words, int cur) {
        if (cur == words.length) {
            ans.add(String.join(" ", path));
            return;
        }

        String word = words[cur];

        Set<String> synonym = map.get(word);


        boolean match = false;

        if (synonym != null) {
            for (String s : synonym) {
                if (used.contains(s)) {
                    continue;
                }

                used.add(s);

                Set<String> set = map.get(s);

                for (String s1 : set) {
                    if (used.contains(s1)) {
                        continue;
                    }

                    match = true;

                    used.add(s1);
                    path.add(s1);

                    process(ans, map, used, path, words, cur + 1);

                    path.removeLast();
                    used.remove(s1);
                }


                used.remove(s);
            }
        }

        if (!match) {
            path.add(word);
            used.add(word);
            process(ans, map, used, path, words, cur + 1);
            path.removeLast();
            used.remove(word);
        }

    }

}