package com.zzzj.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-03-11 15:13
 */
public class Leet1464 {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{1, 5, 4, 5}));
    }

    public static int maxProduct(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(nums.length, Comparator.comparingInt(o -> (int) o).reversed());

        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }

        return (queue.remove() - 1) * (queue.remove() - 1);
    }

}
