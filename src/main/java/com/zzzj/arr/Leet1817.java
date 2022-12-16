package com.zzzj.arr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-11-24 16:08
 */
public class Leet1817 {

    public static int[] findingUsersActiveMinutes(int[][] logs, int k) {

        Map<Integer, Set<Integer>> active = new HashMap<>();

        for (int[] log : logs) {
            active.computeIfAbsent(log[0], integer -> new HashSet<>())
                    .add(log[1]);
        }

        int[] ans = new int[k];

        for (Map.Entry<Integer, Set<Integer>> entry : active.entrySet()) {
            int c = entry.getValue().size();

            ans[c - 1]++;
        }

        return ans;
    }

}
