package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2024-01-04 16:06
 */
public class LCP20 {

    public static void main(String[] args) {

        System.out.println(busRapidTransit(31, 5, 3, new int[]{6}, new int[]{10}));

        System.out.println(busRapidTransit(612, 4, 5, new int[]{3, 6, 8, 11, 5, 10, 4}, new int[]{4, 7, 6, 3, 7, 6, 4}));

    }

    public static int busRapidTransit(int target, int inc, int dec, int[] jump, int[] cost) {
        Map<Integer, Long> f = new HashMap<>();
        return (int) (dfs(target, f, inc, dec, jump, cost) % MOD);
    }

    static final int MOD = 1000000007;

    private static long dfs(
            int cur,
            Map<Integer, Long> f,
            int inc,
            int dec,
            int[] jump,
            int[] cost
    ) {

        if (cur == 0)
            return 0;

        if (cur == 1)
            return inc;

        if (f.containsKey(cur))
            return f.get(cur);

        long min = (long) cur * inc;

        for (int i = 0; i < jump.length; i++) {

            int j = jump[i];

            int mod = cur % j;

            if (mod == 0) {
                min = Math.min(min, dfs(cur / j, f, inc, dec, jump, cost) + cost[i]);
            } else {

                long sub = mod;

                long add = j - mod;

                min = Math.min(min,
                        sub * inc + dfs(cur / j, f, inc, dec, jump, cost) + cost[i]
                );

                min = Math.min(min,
                        add * dec + dfs((cur / j) + 1, f, inc, dec, jump, cost) + cost[i]
                );
            }

        }

        f.put(cur, min);

        return min;
    }


}
