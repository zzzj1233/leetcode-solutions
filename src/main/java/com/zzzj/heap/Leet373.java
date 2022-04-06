package com.zzzj.heap;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-03-29 17:21
 */
public class Leet373 {

    public static void main(String[] args) {
        // [1,1,2]
        // [1,2,3]
        // 10
        System.out.println(kSmallestPairs(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 10));
    }

    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int max = Math.min(nums1.length * nums2.length, k);

        final PriorityQueue<int[]> queue = new PriorityQueue<>(max, Comparator.comparingInt(o -> nums1[o[0]] + nums2[o[1]]));


        queue.add(new int[]{0, 0});

        for (int i = 1; i < nums1.length; i++) {
            queue.add(new int[]{i, 0});
        }

        List<List<Integer>> ans = new ArrayList<>(max);

        while (!queue.isEmpty()) {

            int[] item = queue.remove();

            ans.add(Arrays.asList(nums1[item[0]], nums2[item[1]]));

            if (item[1] + 1 < nums2.length) {
                queue.add(new int[]{item[0], item[1] + 1});
            }

        }

        return ans;
    }

}
