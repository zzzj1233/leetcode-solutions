package com.zzzj.greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-10-12 16:02
 */
public class Leet2244 {

    public static int minimumRounds(int[] tasks) {

        int N = tasks.length;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1);
        }

        int ans = 0;

        for (Integer value : map.values()) {

            if (value == 1) {
                return -1;
            }

            if (value % 3 == 1) {
                ans += 2;
                ans += (value - 4) / 3;
            } else if (value % 3 == 2) {
                ans += 1;
                ans += (value - 2) / 3;
            } else {
                ans += value / 3;
            }

        }

        return ans;
    }

}
