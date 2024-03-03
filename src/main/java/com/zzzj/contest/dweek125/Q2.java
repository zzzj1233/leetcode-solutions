package com.zzzj.contest.dweek125;

import java.util.PriorityQueue;

public class Q2 {

    public static void main(String[] args) {

        System.out.println(minOperations(new int[]{2, 11, 10, 1, 3}, 10));

        System.out.println(minOperations(new int[]{1, 1, 2, 4, 9}, 20));

        System.out.println(minOperations(new int[]{1000000000, 999999999, 1000000000, 999999999, 1000000000, 999999999}, 1000000000));

    }

    public static int minOperations(int[] nums, int k) {

        int N = nums.length;

        PriorityQueue<Long> queue = new PriorityQueue<>(N);

        for (int i = 0; i < N; i++)
            queue.add((long) nums[i]);

        int ans = 0;

        while (queue.size() >= 2 && queue.peek() < k) {

            Long min1 = queue.remove();

            Long min2 = queue.remove();

            queue.add((min1 << 1) + min2);

            ans++;
        }

        return ans;
    }

}
