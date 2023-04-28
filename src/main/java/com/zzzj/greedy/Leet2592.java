package com.zzzj.greedy;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2023-04-24 17:37
 */
public class Leet2592 {

    public static void main(String[] args) {
        // 1, 1, 2, 3, 3, 5
        System.out.println(maximizeGreatness(new int[]{1, 3, 5, 2, 1, 3, 1}));

        System.out.println(maximizeGreatness(new int[]{1, 2, 3, 4}));
    }

    public static int maximizeGreatness(int[] nums) {

        TreeMap<Integer, Integer> treeMap = new TreeMap<>((o1, o2) -> o2 - o1);

        for (int num : nums) {
            treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
        }

        int ans = 0;

        Integer prevCount = null;

        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {

            if (prevCount == null) {
                prevCount = entry.getValue();
                continue;
            }

            Integer count = entry.getValue();

            int pp = prevCount;

            prevCount -= count;

            if (prevCount < 0) {
                prevCount = 0;
            }

            ans += pp - prevCount;

            prevCount += count;
        }

        return ans;
    }

}
