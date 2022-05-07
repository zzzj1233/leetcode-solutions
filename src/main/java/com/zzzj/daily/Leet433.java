package com.zzzj.daily;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-05-07 16:21
 */
public class Leet433 {

    public static void main(String[] args) {
        System.out.println(minMutation("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"}));
    }

    public static int minMutation(String start, String end, String[] bank) {
        Set<String> set = Arrays.stream(bank)
                .collect(Collectors.toSet());

        LinkedList<String> queue = new LinkedList<>();

        Set<String> visited = new HashSet<>();

        visited.add(start);

        queue.add(start);

        int level = -1;

        char[][] candidate = new char[126][];
        candidate['A'] = new char[]{'C', 'G', 'T'};
        candidate['C'] = new char[]{'A', 'G', 'T'};
        candidate['G'] = new char[]{'A', 'C', 'T'};
        candidate['T'] = new char[]{'A', 'C', 'G'};

        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                String first = queue.removeFirst();

                if (first.equals(end)) {
                    return level;
                }

                StringBuilder builder = new StringBuilder(first);

                for (int j = 0; j < first.length(); j++) {
                    char origin = builder.charAt(j);
                    for (char c : candidate[origin]) {
                        builder.setCharAt(j, c);
                        String changed = builder.toString();
                        if (visited.contains(changed)) {
                            continue;
                        }
                        if (set.contains(changed)) {
                            visited.add(changed);
                            queue.add(changed);
                        }
                    }
                    builder.setCharAt(j, origin);
                }
            }
        }

        return -1;
    }

}
