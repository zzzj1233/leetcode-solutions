package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leet1743 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(restoreArray(LeetUtils.convertInts("[[4,-2],[1,4],[-3,1]]"))));
    }

    public static int[] restoreArray(int[][] adjacentPairs) {

        int N = adjacentPairs.length;

        int[] ans = new int[N + 1];

        Map<Integer, int[]> map = new HashMap<>();

        for (int[] pair : adjacentPairs) {
            int x = pair[0];
            int y = pair[1];

            // x和y相邻
            if (!map.containsKey(x)) {
                map.put(x, new int[]{y, -1});
            } else {
                map.get(x)[1] = y;
            }

            if (!map.containsKey(y)) {
                map.put(y, new int[]{x, -1});
            } else {
                map.get(y)[1] = x;
            }
        }

        Map.Entry<Integer, int[]> first = map.entrySet().stream().filter(entry -> entry.getValue()[1] == -1)
                .findFirst()
                .get();

        int index = 0;

        Integer cur = first.getKey();
        int prev = -1;

        while (index <= N) {
            ans[index] = cur;
            int[] neigh = map.get(cur);
            if (neigh[0] == prev) {
                prev = cur;
                cur = neigh[1];
            } else {
                prev = cur;
                cur = neigh[0];
            }
            index++;
        }


        return ans;
    }

}
