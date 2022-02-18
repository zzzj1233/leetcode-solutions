package com.zzzj.window;

import java.util.*;

/**
 * @author zzzj
 * @create 2021-12-15 11:44
 */
public class Leet2245 {

    public static void main(String[] args) {
        System.out.println(findAnagrams("abab", "ab"));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> answer = new LinkedList<>();

        Map<Integer, Integer> indexes = new HashMap<>(p.length());

        Map<Integer, Integer> table = new HashMap<>(p.length());

        for (int i = 0; i < p.length(); i++) {
            int index = p.charAt(i) - 97;
            indexes.put(index, indexes.getOrDefault(index, 0) + 1);
        }

        for (int i = 0; i < p.length(); i++) {
            int index = s.charAt(i) - 97;
            table.put(index, table.getOrDefault(index, 0) + 1);
        }

        if (table.equals(indexes)) {
            answer.add(0);
        }

        int expired = s.charAt(0) - 97;

        for (int j = p.length(); j < s.length(); j++) {

            table.put(expired, table.getOrDefault(expired, 0) - 1);

            int i = j - p.length() + 1;

            expired = (int) s.charAt(i) - 97;

            int index = s.charAt(j) - 97;

            table.put(index, table.getOrDefault(index, 0) + 1);

            if (table.equals(indexes)) {
                answer.add(i);
            }

        }

        return answer;
    }


}
