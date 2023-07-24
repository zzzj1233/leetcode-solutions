package com.zzzj.contest.dweek108;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2023-07-17 17:48
 */
public class Leet2766 {

    public static void main(String[] args) {

        System.out.println(relocateMarbles(
                new int[]{1, 6, 7, 8},
                new int[]{1, 7, 2},
                new int[]{2, 9, 5}
        ));

        System.out.println(relocateMarbles(
                new int[]{1, 1, 3, 3},
                new int[]{1, 3},
                new int[]{2, 2}
        ));

    }

    public static List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {

        Map<Integer, Integer> pos = new HashMap<>(nums.length);

        for (int num : nums) pos.put(num, pos.getOrDefault(num, 0) + 1);

        int N = moveFrom.length;

        for (int i = 0; i < N; i++) {

            int p = moveFrom[i];
            int t = moveTo[i];

            if (p == t) continue;

            pos.put(t, pos.getOrDefault(t, 0) + pos.remove(p));
        }

        return pos.entrySet().stream().filter(entry -> entry.getValue() > 0).map(entry -> entry.getKey())
                .sorted()
                .collect(Collectors.toList());
    }

}
