package com.zzzj.arr;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Leet2007 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findOriginalArray(new int[]{2, 1, 2, 4, 2, 4})));
    }

    static int[] EMPTY = new int[0];

    public static int[] findOriginalArray(int[] changed) {
        int N = changed.length;

        if (N % 2 != 0) {
            return EMPTY;
        }

        int half = N / 2;

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i : changed) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        int[] ans = new int[half];

        Integer zero = map.remove(0);

        if (zero == null) {
            zero = 0;
        }

        if (zero % 2 != 0) {
            return EMPTY;
        }

        int ansIdx = zero;

        while (ansIdx < half && !map.isEmpty()) {
            Map.Entry<Integer, Integer> entry = map.firstEntry();
            Integer num = entry.getKey();
            Integer count = entry.getValue();

            Integer db = map.get(num << 1);

            if (db == null) {
                return EMPTY;
            }

            if (db == 1) {
                map.remove(num << 1);
            } else {
                map.put(num << 1, db - 1);
            }

            if (count == 1) {
                map.pollFirstEntry();
            } else {
                map.put(num, count - 1);
            }

            ans[ansIdx++] = num;
        }

        return map.isEmpty() && ansIdx == half ? ans : EMPTY;
    }

}
