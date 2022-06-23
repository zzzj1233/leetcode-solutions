package com.zzzj.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-06-22 10:10
 */
public class Leet870 {

    public static void main(String[] args) {
    }

    public static int[] advantageCount(int[] nums1, int[] nums2) {
        int N = nums1.length;

        Arrays.sort(nums1);

        PriorityQueue<int[]> queue = new PriorityQueue<>(N, (o1, o2) -> o2[1] - o1[1]);

        for (int i = 0; i < nums2.length; i++) {
            queue.add(new int[]{i, nums2[i]});
        }

        int l = 0;
        int r = N - 1;

        int[] ans = new int[N];

        while (!queue.isEmpty()) {
            int[] first = queue.remove();
            int index = first[0];
            int value = first[1];
            if (r >= 0 && value >= nums1[r]) {
                ans[index] = nums1[l++];
            } else {
                ans[index] = nums1[r--];
            }
        }

        return ans;
    }


}
