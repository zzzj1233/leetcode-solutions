package com.zzzj.uf;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.Unresolved;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-09-19 15:20
 */
@Unresolved
public class Leet721 {

    public static void main(String[] args) {
        System.out.println(accountsMerge(LeetUtils.convertListStrings("[[\"David\",\"David0@m.co\",\"David1@m.co\"],[\"David\",\"David3@m.co\",\"David4@m.co\"],[\"David\",\"David4@m.co\",\"David5@m.co\"],[\"David\",\"David2@m.co\",\"David3@m.co\"],[\"David\",\"David1@m.co\",\"David2@m.co\"]]")));
    }


    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        int N = accounts.size();

        Map<Integer, TreeSet<String>> emailMap = new HashMap<>(N);

        Map<Integer, String> nameMap = new HashMap<>(N);

        Map<String, Integer> indexMap = new HashMap<>();

        int count = 0;

        for (List<String> account : accounts) {
            String name = account.get(0);

            TreeSet<String> set = account.stream().skip(1).collect(Collectors.toCollection(TreeSet::new));

            int existsIndex = -1;

            for (String email : set) {
                // 这个email出现过了
                if (indexMap.containsKey(email)) {
                    existsIndex = indexMap.get(email);
                    break;
                }
            }

            if (existsIndex != -1) {
                for (String email : set) {
                    indexMap.put(email, existsIndex);
                }
                emailMap.get(existsIndex).addAll(set);
            } else {
                emailMap.put(count, set);
                nameMap.put(count, name);
                for (String email : set) {
                    indexMap.put(email, count);
                }
                count++;
            }
        }

        return emailMap.entrySet().stream().map(entry -> {
            Integer index = entry.getKey();
            TreeSet<String> emails = entry.getValue();
            ArrayList<String> list = new ArrayList<>(emails.size() + 1);
            list.add(nameMap.get(index));
            list.addAll(emails);
            return list;
        }).collect(Collectors.toList());
    }

}
