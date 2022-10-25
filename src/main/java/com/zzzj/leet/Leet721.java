package com.zzzj.leet;

import java.util.*;
import java.util.stream.Collectors;

public class Leet721 {


    public static void main(String[] args) {
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {

        Map<Integer, Set<String>> map1 = new HashMap<>();

        Map<String, Integer> map2 = new HashMap<>();

        Map<Integer, String> map3 = new HashMap<>();

        for (List<String> account : accounts) {

            String name = account.get(0);

            Set<String> emails = account.stream().skip(1).collect(Collectors.toSet());

            List<Integer> connection = new ArrayList<>();

            for (String email : emails) {
                if (map2.containsKey(email)) {
                    connection.add(map2.get(email));
                }
            }

            int id = System.identityHashCode(name);

            map1.put(id, emails);
            map3.put(id, name);

            for (String email : emails) {
                map2.put(email, id);
            }

            // 全部链接到一起
            for (int i = 0; i < connection.size(); i++) {

                Set<String> exists = map1.remove(connection.get(i));

                map1.get(id).addAll(exists);

                for (String exist : exists) {
                    map2.put(exist, id);
                }
            }

        }


        return map1.entrySet().stream()
                .map(entry -> {
                    LinkedList<String> list = new LinkedList<>(entry.getValue());
                    list.addFirst(map3.get(entry.getKey()));
                    list.sort(String::compareTo);
                    return list;
                }).collect(Collectors.toList());
    }


}
