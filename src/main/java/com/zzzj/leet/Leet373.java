package com.zzzj.leet;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-05-13 15:49
 */
public class Leet373 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> (nums1[o[0]] + nums2[o[1]])));

        for (int i = 0; i < nums2.length; i++) {
            queue.add(new int[]{0, i});
        }

        List<List<Integer>> ans = new ArrayList<>(k);

        while (!queue.isEmpty() && ans.size() < k) {
            int[] item = queue.remove();
            int i = item[0];
            int j = item[1];
            ans.add(Arrays.asList(nums1[i], nums2[j]));
            if (i + 1 < nums1.length) {
                queue.add(new int[]{i + 1, j});
            }
        }

        return ans;
    }

}
