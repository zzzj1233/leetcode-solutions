package com.zzzj.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-03-11 17:09
 */
public class Leet2398 {

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> (int) o).reversed());

        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }


        for (int i = 0; i < k - 1; i++) {
            queue.remove();
        }

        return queue.remove();
    }

}
