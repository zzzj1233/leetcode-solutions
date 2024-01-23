package com.zzzj.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzzj
 * @create 2024-01-18 11:22
 */
public class Leet1931 {

    public static void main(String[] args) {

        System.out.println(colorTheGrid(1, 1));

        System.out.println(colorTheGrid(2, 3));

    }

    public static final int MOD = 1000000007;

    public static int colorTheGrid(int m, int n) {

        Map<Integer, List<Integer>> states = new HashMap<>();

        int limit = (int) Math.pow(3, m);

        OUTER:
        for (int stat = 0; stat < limit; stat++) {

            int prev = -1;

            int s = stat;

            for (int j = 0; j < m; j++) {

                int mod = s % 3;

                if (mod == prev)
                    continue OUTER;

                prev = mod;
                s /= 3;
            }

            states.put(stat, new ArrayList<>());
        }

        for (Map.Entry<Integer, List<Integer>> entry : states.entrySet()) {

            Integer stat = entry.getKey();

            OUTER:
            for (Integer prev : states.keySet()) {

                int s = stat;

                int p = prev;

                for (int i = 0; i < m; i++) {
                    if (s % 3 == p % 3)
                        continue OUTER;
                    s /= 3;
                    p /= 3;
                }

                entry.getValue().add(prev);
            }

        }

        int[] f = new int[limit + 1];

        for (Integer stat : states.keySet())
            f[stat] = 1;

        for (int i = 1; i < n; i++) {

            int[] next = new int[limit + 1];

            for (Map.Entry<Integer, List<Integer>> entry : states.entrySet())
                for (Integer ps : entry.getValue())
                    next[entry.getKey()] = (next[entry.getKey()] + f[ps]) % MOD;

            f = next;
        }

        int ans = 0;

        for (Integer stat : states.keySet())
            ans = (ans + f[stat]) % MOD;

        return ans;
    }

    public static int vs1 = 0b100;

    public static int vs2 = 0b010;

    public static int vs3 = 0b001;

    public static int and = 0b111;

    // 二进制版
    public static int colorTheGrid_bin(int m, int n) {

        int limit = 1 << (3 * m);

        Map<Integer, List<Integer>> states = new HashMap<>();

        OUTER:
        for (int stat = 0; stat < limit; stat++) {

            if (Integer.bitCount(stat) != m)
                continue;

            int prev = 0;

            // 每3位一个状态
            for (int i = 0; i < m; i++) {

                int s = (stat >>> (i * 3)) & and;

                if (s != vs1 && s != vs2 && s != vs3)
                    continue OUTER;

                if (s == prev)
                    continue OUTER;

                prev = s;
            }

            states.put(stat, new ArrayList<>());
        }

        // 计算可用的父状态
        for (Map.Entry<Integer, List<Integer>> entry : states.entrySet()) {
            List<Integer> list = entry.getValue();
            for (Integer s2 : states.keySet()) {
                if ((s2 & entry.getKey()) == 0)
                    list.add(s2);
            }
        }

        int[] f = new int[limit];

        for (Integer stat : states.keySet())
            f[stat] = 1;

        for (int i = 1; i < n; i++) {

            int[] next = new int[limit];

            for (Map.Entry<Integer, List<Integer>> entry : states.entrySet())
                for (Integer ps : entry.getValue())
                    next[entry.getKey()] = (next[entry.getKey()] + f[ps]) % MOD;

            f = next;
        }

        int ans = 0;

        int last = (n - 1) % 2;

        for (Integer stat : states.keySet())
            ans = (ans + f[stat]) % MOD;

        return ans;
    }

}
