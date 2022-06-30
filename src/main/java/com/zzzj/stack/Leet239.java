package com.zzzj.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2021-12-05 21:54
 */

public class Leet239 {

    public static void main(String[] args) {
        // 3 3 2 5
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3)));
    }


    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> queue = new LinkedList<>();

        int n = nums.length;

        int[] answer = new int[n - k + 1];

        queue.add(0);

        for (int i = 1; i < k; i++) {
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                queue.removeLast();
            }
            queue.addLast(i);
        }

        for (int i = k - 1; i < nums.length; i++) {

            while (!queue.isEmpty() && queue.peekFirst() < i - k + 1) {
                queue.removeFirst();
            }

            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                queue.removeLast();
            }

            queue.addLast(i);

            answer[i - k + 1] = nums[queue.peekFirst()];
        }

        return answer;
    }

}
