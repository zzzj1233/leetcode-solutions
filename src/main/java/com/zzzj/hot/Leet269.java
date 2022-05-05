package com.zzzj.hot;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-05-05 15:30
 */
public class Leet269 {

    public static void main(String[] args) {
        System.out.println(alienOrder(new String[]{"ac", "ab", "zc", "zb"}));
    }

    public static String alienOrder(String[] words) {
        // ac
        // ab
        // zc
        // zb

        // c > b
        // a > z

        // w > e > r > t > f
        Map<Character, Integer> inOrder = new HashMap<>(words.length << 1);
        Map<Character, Set<Character>> dependency = new HashMap<>(words.length << 1);

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                inOrder.putIfAbsent(word.charAt(i), 0);
            }
        }

        OUTER:
        for (int i = 1; i < words.length; i++) {
            String prev = words[i - 1];
            String cur = words[i];
            int len = Math.min(prev.length(), cur.length());

            for (int j = 0; j < len; j++) {
                // 不一样
                if (prev.charAt(j) != cur.charAt(j)) {
                    // record
                    Set<Character> dep = dependency.get(prev.charAt(j));
                    if (dep == null || !dep.contains(cur.charAt(j))) {
                        inOrder.put(cur.charAt(j), inOrder.get(cur.charAt(j)) + 1);
                        dependency.computeIfAbsent(prev.charAt(j), character -> new HashSet<>())
                                .add(cur.charAt(j));
                    }
                    continue OUTER;
                }
            }

            if (prev.length() > cur.length()) {
                return "";
            }
        }

        // 拓扑排序
        LinkedList<Character> queue = new LinkedList<>();

        for (Map.Entry<Character, Integer> entry : inOrder.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        StringBuilder builder = new StringBuilder(inOrder.size());

        int size = 0;

        while (!queue.isEmpty()) {
            Character first = queue.removeFirst();
            builder.append(first);
            Set<Character> dependencies = dependency.get(first);
            if (dependencies != null) {
                for (Character character : dependencies) {
                    inOrder.put(character, inOrder.get(character) - 1);
                    if (inOrder.get(character) == 0) {
                        queue.add(character);
                    }
                }
            }
            size++;
        }

        return size == inOrder.size() ? builder.toString() : "";
    }

}
