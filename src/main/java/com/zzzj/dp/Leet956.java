package com.zzzj.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2024-01-16 17:20
 */
public class Leet956 {

    public static void main(String[] args) {

        System.out.println(tallestBillboard(new int[]{1, 2, 3, 6}));

        System.out.println(tallestBillboard(new int[]{1, 2, 3, 4, 5, 6}));

    }

    public static int tallestBillboard(int[] rods) {

        int sum = Arrays.stream(rods).sum();

        Map<Integer, Integer> f = new HashMap<>();

        f.put(0, 0);

        for (int rod : rods) {

            Map<Integer, Integer> next = new HashMap<>(f);

            for (Map.Entry<Integer, Integer> entry : f.entrySet()) {

                int k1 = entry.getKey() + rod;

                int k2 = entry.getKey() - rod;

                next.put(
                        k1,
                        Math.max(
                                entry.getValue() + rod,
                                next.getOrDefault(k1, 0)
                        )
                );
                next.put(
                        k2,
                        Math.max(
                                entry.getValue() + rod,
                                next.getOrDefault(k2, 0)
                        )
                );
            }

            f = next;
        }

        return f.get(0) / 2;
    }


}
