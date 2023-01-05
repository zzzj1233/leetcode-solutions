package com.zzzj.stack;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-01-04 18:02
 */
public class Leet1063 {

    public static void main(String[] args) {
        System.out.println(validSubarrays(new int[]{1, 4, 2, 5, 3}));
        System.out.println(validSubarrays(new int[]{2, 2, 2}));
        System.out.println(validSubarrays(new int[]{3, 2, 1}));
    }

    public static int validSubarrays(int[] nums) {
        // 子数组最左边的元素不大于子数组中的其他元素

        int N = nums.length;

        LinkedList<Integer> stack = new LinkedList<>();

        int ans = 0;

        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && nums[i] < nums[stack.peekLast()]) {
                ans += i - stack.removeLast();
            }

            stack.add(i);
        }

        if (!stack.isEmpty()) {
            Integer firstIndex = stack.peekFirst() - 1;

            while (!stack.isEmpty()) {
                ans += N - stack.removeLast();
            }

        }

        return ans;
    }

}
