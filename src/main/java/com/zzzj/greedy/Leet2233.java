package com.zzzj.greedy;

import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-10-12 16:16
 */
public class Leet2233 {

    public static void main(String[] args) {
        System.out.println(maximumProduct(new int[]{0, 4}, 5));
    }

    public static int maximumProduct(int[] nums, int k) {

        final int MOD = 1000000007;

        int N = nums.length;

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        // 1. 把所有0变成1
        for (int i = 0; i < N; i++) {
            if (nums[i] == 0) {
                if (k == 0) {
                    return 0;
                }
                k--;
                nums[i] = 1;
            }
            queue.add(nums[i]);
        }

        // 2. 减少元素差
        while (k > 0) {
            queue.add(queue.remove() + 1);
            k--;
        }

        long ans = 1;

        while (!queue.isEmpty()) {
            ans *= queue.remove();
            ans %= MOD;
        }

        return (int) ans;
    }


}
