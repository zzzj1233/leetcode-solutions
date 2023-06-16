package com.zzzj.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2023-06-14 17:06
 */
public class Leet609 {

    public static void main(String[] args) {
        System.out.println(findDuplicate(
                new String[]{"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"}
        ));
    }

    public static List<List<String>> findDuplicate(String[] paths) {

        Map<String, List<String>> rec = new HashMap<>();

        for (String path : paths) {

            String[] words = path.split(" ");

            String root = words[0];

            for (int i = 1; i < words.length; i++) {

                String word = words[i];

                int left = word.indexOf('(');

                String content = word.substring(left + 1, word.lastIndexOf(')'));

                rec.computeIfAbsent(content, s -> new ArrayList<>())
                        .add(root + "/" + word.substring(0, left));
            }

        }

        return rec.values().stream().filter(list -> list.size() > 1).collect(Collectors.toList());
    }

}
