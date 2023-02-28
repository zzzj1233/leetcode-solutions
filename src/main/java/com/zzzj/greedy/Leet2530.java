package com.zzzj.greedy;

import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-02-27 17:50
 */
public class Leet2530 {

    public static long maxKelements(int[] nums, int k) {

        int N = nums.length;

        PriorityQueue<Integer> queue = new PriorityQueue<>(N, (o1, o2) -> o2 - o1);

        for (int num : nums) {
            queue.add(num);
        }

        long ans = 0;

        while (k > 0 && !queue.isEmpty()) {
            Integer remove = queue.remove();

            ans += remove;

            int ceil = (int) Math.ceil(remove / 3.0);

            if (ceil > 0) {
                queue.add(ceil);
            }

            k--;
        }

        return ans;
    }

}
