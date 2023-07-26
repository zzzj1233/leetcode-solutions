package com.zzzj.contest.week354;

import java.util.Map;
import java.util.TreeMap;

public class Leet6929 {

    public static void main(String[] args) {

        System.out.println(maximumBeauty(new int[]{4, 6, 1, 2}, 2));

        System.out.println(maximumBeauty(new int[]{1, 1, 1, 1}, 10));

    }

    public static int maximumBeauty(int[] nums, int k) {

        Map<Integer, Integer> diff = new TreeMap<>();

        for (int num : nums) {

            int v1 = num - k;

            int v2 = num + k + 1;

            diff.put(v1, diff.getOrDefault(v1, 0) + 1);

            diff.put(v2, diff.getOrDefault(v2, 0) - 1);
        }

        int ans = 0;

        int cur = 0;

        for (Map.Entry<Integer, Integer> entry : diff.entrySet()) {
            cur += entry.getValue();
            ans = Math.max(ans, cur);
        }


        return ans;
    }

}
