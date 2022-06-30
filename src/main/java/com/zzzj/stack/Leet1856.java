package com.zzzj.stack;

import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2021-12-12 12:17
 */
public class Leet1856 {

    // 子数组中 最小的值 * sum , 求最大值

    public static void main(String[] args) {
        System.out.println(maxSumMinProduct(new int[]{1, 2, 3, 2}));
    }

    public static int maxSumMinProduct(int[] nums) {
        int n = nums.length;

        int answer = -1;

        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        LinkedList<Integer> stack = new LinkedList<>();

        stack.add(0);


        for (int i = 1; i < n; i++) {
            int value = nums[i];

            while (!stack.isEmpty() && nums[stack.peekLast()] >= value) {
                // 算answer
                Integer last = stack.removeLast();
                int compareVal = nums[last] * (prefixSum[i - 1] - (stack.isEmpty() ? 0 : prefixSum[stack.peekLast()]));
                answer = Math.max(answer, compareVal);
            }

            stack.addLast(i);
        }

        while (!stack.isEmpty()) {
            Integer last = stack.removeLast();
            int compareVal = nums[last] * (prefixSum[n - 1] - (stack.isEmpty() ? 0 : prefixSum[stack.peekLast()]));
            answer = Math.max(answer, compareVal);
        }

        return answer;
    }


}
