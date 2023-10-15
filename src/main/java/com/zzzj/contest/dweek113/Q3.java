package com.zzzj.contest.dweek113;

import com.zzzj.leet.LeetUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q3 {

    public static void main(String[] args) {

        System.out.println(countPairs(LeetUtils.convertLists("[[1,2],[4,2],[1,3],[5,2]]"), 5));

        System.out.println(countPairs(LeetUtils.convertLists("[[1,3],[1,3],[1,3],[1,3],[1,3]]"), 0));

    }

    public static int countPairs(List<List<Integer>> coordinates, int k) {

        Map<Integer, Map<Integer, Integer>> rec = new HashMap<>();

        int ans = 0;

        for (int i = 0; i < coordinates.size(); i++) {

            List<Integer> coordinate = coordinates.get(i);

            Integer first = coordinate.get(0);

            Integer sec = coordinate.get(1);

            for (int j = 0; j <= k; j++) {

                Map<Integer, Integer> m2 = rec.getOrDefault(j ^ first, Collections.emptyMap());

                ans += m2.getOrDefault((k - j) ^ sec, 0);
            }

            Map<Integer, Integer> inner = rec.computeIfAbsent(first, integer -> new HashMap<>());
            inner.put(sec, inner.getOrDefault(sec, 0) + 1);
        }

        return ans;
    }

}
