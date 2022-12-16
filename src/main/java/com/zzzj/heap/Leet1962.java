package com.zzzj.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-12-05 12:02
 */
public class Leet1962 {

    public static int minStoneSum(int[] piles, int k) {

        PriorityQueue<Integer> queue = new PriorityQueue<>(piles.length, (o1, o2) -> o2 - o1);

        for (int pile : piles) {
            queue.add(pile);
        }

        while (k > 0 && !queue.isEmpty()) {
            Integer max = queue.remove();
            max -= ((int) Math.floor(max / 2.0));
            if (max > 0) {
                queue.add(max);
            }
            k--;
        }

        return queue.stream().mapToInt(value -> value).sum();
    }

}
