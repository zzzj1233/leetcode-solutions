package com.zzzj.stack;

import sun.plugin.WJcovUtil;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2021-12-12 12:17
 */
public class Leet1856 {

    public static void main(String[] args) {
        System.out.println(maxSumMinProduct(new int[]{1, 2, 3, 2}));
        System.out.println(maxSumMinProduct(new int[]{2, 3, 3, 1, 2}));
        System.out.println(maxSumMinProduct(new int[]{3, 1, 5, 6, 4, 2}));
    }

    public static int maxSumMinProduct(int[] nums) {

        int N = nums.length;

        int[] left = new int[N];
        int[] right = new int[N];

        Arrays.fill(left, -1);
        Arrays.fill(right, N);

        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && num < nums[stack.peekLast()]) {
                right[stack.removeLast()] = i;
            }
            stack.add(i);
        }

        stack.clear();

        for (int i = N - 1; i >= 0; i--) {
            int num = nums[i];
            while (!stack.isEmpty() && num < nums[stack.peekLast()]) {
                left[stack.removeLast()] = i;
            }
            stack.add(i);
        }

        // 前缀和
        long[] preSum = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        long ans = 0;

        final int MOD = 1000000007;

        for (int i = 0; i < N; i++) {
            // 以num为最小值
            int num = nums[i];

            int leftMin = left[i];
            int rightMin = right[i];

            long sum = preSum[rightMin] - preSum[leftMin + 1];

            ans = Math.max(ans, (sum * num));
        }

        return (int) (ans % MOD);
    }


}
