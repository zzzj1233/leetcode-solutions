package com.zzzj.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-03-11 17:42
 */
public class Leet1046 {

    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(stones.length, Comparator.comparingInt(o -> (int) o).reversed());

        for (int i = 0; i < stones.length; i++) {
            queue.add(stones[i]);
        }

        while (queue.size() > 1) {
            Integer first = queue.remove();
            Integer second = queue.remove();
            int sub = first - second;
            if (sub != 0) {
                queue.add(sub);
            }
        }

        return queue.isEmpty() ? 0 : queue.remove();
    }

}
