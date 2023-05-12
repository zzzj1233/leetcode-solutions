package com.zzzj.design;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zzzj
 * @create 2023-05-12 15:19
 */
public class Leet703 {


    private static class KthLargest {

        private final PriorityQueue<Integer> pq;

        private final int k;

        public KthLargest(int k, int[] nums) {

            pq = new PriorityQueue<>(k);

            this.k = k;

            for (int num : nums) {
                addToQueue(num);
            }

        }

        public int add(int val) {
            addToQueue(val);
            return pq.peek();
        }

        public void addToQueue(int value) {
            if (this.pq.size() < k) {
                this.pq.add(value);
            } else {
                if (value > pq.peek()) {
                    pq.remove();
                    pq.add(value);
                }
            }
        }

    }

}
