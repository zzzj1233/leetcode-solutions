package com.zzzj.window;

import java.util.*;

/**
 * @author zzzj
 * @create 2021-12-22 18:55
 */
public class Leet187 {

    public static List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 11) {
            return Collections.emptyList();
        }

        Set<String> set = new HashSet<>();

        Map<String, Integer> map = new HashMap<>(s.length());

        map.put(s.substring(0, 10), 0);

        for (int i = 1; i < s.length() - 9; i++) {
            String substring = s.substring(i, i + 10);
            if (map.containsKey(substring)) {
                set.add(substring);
            } else {
                map.put(substring, i);
            }
        }

        return new ArrayList<>(set);
    }

}
