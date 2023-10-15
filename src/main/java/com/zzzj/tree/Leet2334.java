package com.zzzj.tree;

import java.util.LinkedList;

public class Leet2334 {

    public static void main(String[] args) {

        System.out.println(validSubarraySize(new int[]{1, 3, 4, 3, 1}, 6));
        System.out.println(validSubarraySize(new int[]{6, 5, 6, 5, 8}, 7));
        System.out.println(validSubarraySize(new int[]{6, 4, 3, 2, 2, 2}, 10));

    }

    public static int validSubarraySize(int[] nums, int threshold) {

        int N = nums.length;

        int[] left = new int[N];
        int[] right = new int[N];

        // 先求left
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            int num = nums[i];

            while (!queue.isEmpty() && nums[queue.peekLast()] >= num) {
                // rm的right暂时无法计算, 因为相同元素也会把他弹出栈
                queue.removeLast();
            }

            if (queue.isEmpty())
                left[i] = 0;
            else
                left[i] = queue.peekLast() + 1;

            queue.add(i);
        }

        queue.clear();

        for (int i = N - 1; i >= 0; i--) {
            int num = nums[i];

            while (!queue.isEmpty() && nums[queue.peekLast()] >= num) {
                queue.removeLast();
            }

            if (queue.isEmpty())
                right[i] = N - 1;
            else
                right[i] = queue.peekLast() - 1;

            queue.add(i);
        }

        for (int i = 0; i < N; i++) {

            double cnt = right[i] - left[i] + 1D;

            if (nums[i] > threshold / cnt)
                return (int) cnt;
        }

        return -1;
    }


}
