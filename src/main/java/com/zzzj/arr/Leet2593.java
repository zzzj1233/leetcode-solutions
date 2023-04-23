package com.zzzj.arr;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-04-23 17:50
 */
public class Leet2593 {


    public static void main(String[] args) {

        System.out.println(findScore(new int[]{2, 1, 3, 4, 5, 2}));

        System.out.println(findScore(new int[]{2, 3, 5, 1, 3, 2}));

    }

    public static long findScore(int[] nums) {

        int N = nums.length;

        PriorityQueue<Integer> queue = new PriorityQueue<>(N, Comparator.comparingInt(index -> nums[(int) index]).thenComparingInt(index -> (int) index));

        for (int i = 0; i < N; i++) {
            queue.add(i);
        }

        boolean[] marked = new boolean[N];

        long ans = 0;

        while (!queue.isEmpty()) {

            Integer index = queue.remove();

            if (marked[index]) {
                continue;
            }

            for (int i = -1; i < 2; i++) {
                safeMark(marked, index + i);
            }

            ans += nums[index];
        }

        return ans;
    }

    public static void safeMark(boolean[] marked, int index) {
        if (index < 0 || index >= marked.length) {
            return;
        }
        marked[index] = true;
    }


}
