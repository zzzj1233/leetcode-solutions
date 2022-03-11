package com.zzzj.heap;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-03-11 17:03
 */
public class Leet2382 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(nums.length, Comparator.comparingInt(o -> ((int[]) o)[1]).reversed());

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.add(new int[]{entry.getKey(), entry.getValue()});
        }

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            ans[i] = queue.remove()[0];
        }

        return ans;
    }

}
