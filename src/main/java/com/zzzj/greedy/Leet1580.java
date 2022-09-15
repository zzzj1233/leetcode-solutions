package com.zzzj.greedy;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2022-09-08 17:31
 */
public class Leet1580 {

    public static void main(String[] args) {
        System.out.println(maxBoxesInWarehouse(new int[]{1, 2, 2, 3, 4}, new int[]{3, 4, 1, 2}));
    }

    public static int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        Arrays.sort(boxes);

        int N = warehouse.length;

        int[] left = new int[N];
        int[] right = new int[N];

        left[0] = warehouse[0];
        right[N - 1] = warehouse[N - 1];

        for (int i = 1; i < N; i++) {
            left[i] = Math.min(warehouse[i], left[i - 1]);
        }

        for (int i = N - 2; i >= 0; i--) {
            right[i] = Math.min(warehouse[i], right[i + 1]);
        }

        int[] min = new int[N];


        for (int i = 0; i < N; i++) {
            min[i] = Math.max(left[i], right[i]);
        }

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for (int box : boxes) {
            treeMap.put(box, treeMap.getOrDefault(box, 0) + 1);
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {
            Map.Entry<Integer, Integer> low = treeMap.floorEntry(min[i]);

            if (low != null) {
                if (low.getValue() - 1 == 0) {
                    treeMap.remove(low.getKey());
                } else {
                    treeMap.put(low.getKey(), low.getValue() - 1);
                }
                ans++;
            }
        }

        return ans;
    }

}
