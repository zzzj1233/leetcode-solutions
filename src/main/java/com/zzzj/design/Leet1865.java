package com.zzzj.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-11-24 16:44
 */
public class Leet1865 {

    public static void main(String[] args) {
        FindSumPairs pairs = new FindSumPairs(
                new int[]{1, 1, 2, 2, 2, 3},
                new int[]{1, 4, 5, 2, 5, 4}
        );

        System.out.println(pairs.count(7));
    }


    private static class FindSumPairs {

        private final int[] nums1;

        private final int[] nums2;

        Map<Integer, Integer> count = new HashMap<>();

        public FindSumPairs(int[] nums1, int[] nums2) {
            for (int num : nums2) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }
            this.nums1 = nums1;
            this.nums2 = nums2;
        }

        public void add(int index, int val) {
            int origin = nums2[index];
            count.put(origin, count.getOrDefault(origin, 0) - 1);
            nums2[index] += val;
            int num = nums2[index];
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        public int count(int tot) {
            int ans = 0;

            for (int num : nums1) {
                ans += count.getOrDefault(Math.abs(num - tot), 0);
            }

            return ans;
        }

    }


}
