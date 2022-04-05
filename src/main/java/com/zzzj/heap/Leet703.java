package com.zzzj.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Zzzj
 * @create 2022-03-12 21:12
 */
public class Leet703 {

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 5, 5});
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }


    static class KthLargest {

        private final PriorityQueue<Integer> queue;

        private int k;

        public KthLargest(int k, int[] nums) {
            this.queue = new PriorityQueue<>(k, Comparator.comparingInt(o -> o));
            this.k = k;
            for (int i = 0; i < nums.length; i++) {
                queue.add(nums[i]);
            }
            for (int i = 0; i < k - 1; i++) {
                queue.remove();
            }
        }

        public int add(int val) {
            queue.add(val);
            return val < queue.peek() ? queue.peek() : queue.remove();
        }

    }


}
