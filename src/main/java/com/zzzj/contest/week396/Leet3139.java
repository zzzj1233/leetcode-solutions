package com.zzzj.contest.week396;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2024-05-30 16:05
 */
public class Leet3139 {

    public static void main(String[] args) {

        System.out.println(minCostToEqualizeArray(
                new int[]{1, 14, 14, 15},
                2,
                1
        ));
//
//        System.out.println(minCostToEqualizeArray(
//                new int[]{2, 3, 3, 3, 5},
//                2,
//                1
//        ));

    }

    public static int minCostToEqualizeArray(int[] nums, int cost1, int cost2) {

        int N = nums.length;

        long MOD = 1000000007;

        int largest = Arrays.stream(nums).max().orElse(0);

        boolean can = (cost1 << 1) > cost2;

        PriorityQueue<Integer> queue = new PriorityQueue<>(N);

        for (int num : nums)
            if (num < largest)
                queue.add(largest - num);

        long ans = 0;

        while (!queue.isEmpty()) {

            Integer first = queue.remove();

            Integer sec = null;

            if (can && !queue.isEmpty())
                sec = queue.remove();

            if (sec != null) {
                ans += cost2;
                if (first - 1 > 0)
                    queue.add(first - 1);
                if (sec - 1 > 0)
                    queue.add(sec - 1);
            } else {
                ans += cost1;
                if (first - 1 > 0)
                    queue.add(first - 1);
            }

        }

        return (int) (ans % MOD);
    }

}
