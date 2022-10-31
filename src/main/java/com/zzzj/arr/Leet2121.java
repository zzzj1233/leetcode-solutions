package com.zzzj.arr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leet2121 {

    public static long[] getDistances(int[] arr) {

        Map<Integer, Set<Integer>> indexes = new HashMap<>();

        int N = arr.length;

        for (int i = 0; i < N; i++) {
            indexes.computeIfAbsent(arr[i], integer -> new HashSet<>())
                    .add(i);
        }

        long[] ans = new long[N];

        for (int i = 0; i < N; i++) {

            Set<Integer> set = indexes.get(arr[i]);

            long result = 0;

            for (Integer index : set) {
                result += Math.abs(index - i);
            }

            ans[i] = result;
        }

        return ans;
    }

}
