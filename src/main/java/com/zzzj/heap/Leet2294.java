package com.zzzj.heap;

import java.util.PriorityQueue;

/**
 * @author Zzzj
 * @create 2022-03-17 17:20
 */
public class Leet2294 {

    public static int[] getLeastNumbers(int[] arr, int k) {
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
