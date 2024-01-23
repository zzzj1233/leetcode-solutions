package com.zzzj.arr;

import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2024-01-18 17:46
 */
public class Leet2163 {

    public static void main(String[] args) {

        System.out.println(minimumDifference(new int[]{7, 9, 5, 8, 1, 3}));

    }

    public static long minimumDifference(int[] nums) {

        int N = nums.length;

        int K = N / 3;

        long[] right = new long[N];

        long[] left = new long[N];

        long ls = 0;

        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(K, (o1, o2) -> o2 - o1);

        for (int i = 0; i < N; i++) {
            if (maxQueue.size() < K) {
                maxQueue.add(nums[i]);
                ls += nums[i];
            } else if (nums[i] < maxQueue.peek()) {
                ls = ls - maxQueue.remove() + nums[i];
                maxQueue.add(nums[i]);
            }
            left[i] = ls;
        }

        long rs = 0;

        PriorityQueue<Integer> minQueue = new PriorityQueue<>(K);

        for (int i = N - 1; i >= 0; i--) {
            if (minQueue.size() < K) {
                minQueue.add(nums[i]);
                rs += nums[i];
            } else if (nums[i] > minQueue.peek()) {
                rs = rs - minQueue.remove() + nums[i];
                minQueue.add(nums[i]);
            }
            right[i] = rs;
        }

        long ans = Long.MAX_VALUE;

        for (int i = K; i <= N - K; i++) {

            long ld = left[i - 1];

            long rd = right[i];

            ans = Math.min(
                    ans,
                    ld - rd
            );

        }

        return ans;
    }

}
