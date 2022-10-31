package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Leet2021 {

    public static void main(String[] args) {
        System.out.println(brightestPosition(LeetUtils.convertInts("[[-3,2],[1,2],[3,3]]")));
    }

    public static int brightestPosition(int[][] lights) {
        // 起点 + 1 , 终点 + 1 的 position - 1
        TreeMap<Integer, Integer> record = new TreeMap<>();

        for (int[] light : lights) {
            int position = light[0];
            int range = light[1];

            int start = position - range;
            int end = position + range + 1;

            record.put(start, record.getOrDefault(start, 0) + 1);
            record.put(end, record.getOrDefault(end, 0) - 1);
        }

        int ans = 0;

        int max = 0;

        int pre = 0;

        Set<Map.Entry<Integer, Integer>> entries = record.entrySet();

        for (Map.Entry<Integer, Integer> entry : entries) {
            Integer position = entry.getKey();
            Integer value = entry.getValue();

            pre += value;

            if (pre > max) {
                ans = position;
                max = pre;
            }
        }

        return ans;
    }

}
