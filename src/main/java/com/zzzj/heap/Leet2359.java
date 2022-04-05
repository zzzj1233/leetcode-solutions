package com.zzzj.heap;

import java.util.PriorityQueue;

/**
 * @author Zzzj
 * @create 2022-03-13 10:36
 */
public class Leet2359 {

    public static int[] smallestK(int[] arr, int k) {

        if (arr.length == 0) {
            return new int[]{};
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(arr.length);

        for (int i : arr) {
            queue.add(i);
        }

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            ans[i] = queue.remove();
        }

        return ans;
    }

}
