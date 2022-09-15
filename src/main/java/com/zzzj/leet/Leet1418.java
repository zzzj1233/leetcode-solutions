package com.zzzj.leet;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-09-15 18:07
 */
public class Leet1418 {

    public static List<List<String>> displayTable(List<List<String>> orders) {

        TreeMap<Integer, TreeMap<String, Integer>> map = new TreeMap<>();

        TreeSet<String> foods = new TreeSet<>();

        for (List<String> order : orders) {
            int table = Integer.parseInt(order.get(1));
            String food = order.get(2);

            TreeMap<String, Integer> innerMap = map.computeIfAbsent(table, ignore -> new TreeMap<String, Integer>());
            innerMap.put(food, innerMap.getOrDefault(food, 0) + 1);
            foods.add(food);
        }

        List<String> head = new ArrayList<>(foods.size() + 1);
        head.add("Table");
        head.addAll(foods);

        List<List<String>> ans = new ArrayList<>(map.size() + 1);

        ans.add(head);

        for (Map.Entry<Integer, TreeMap<String, Integer>> entry : map.entrySet()) {
            Integer table = entry.getKey();
            TreeMap<String, Integer> foodMapping = entry.getValue();

            List<String> line = new ArrayList<>(foods.size() + 1);
            line.add(String.valueOf(table));
            line.addAll(
                    foods.stream()
                            .map(s -> foodMapping.getOrDefault(s, 0))
                            .map(Object::toString)
                            .collect(Collectors.toList())
            );
            ans.add(line);
        }

        return ans;
    }

}
