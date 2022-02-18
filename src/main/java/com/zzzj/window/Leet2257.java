package com.zzzj.window;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-01-20 16:19
 */
public class Leet2257 {

    // 滑动窗口的位置                最大值
    // ---------------               -----
    // [1  3  -1] -3  5  3  6  7      3
    // 1 [3  -1  -3] 5  3  6  7       3
    // 1  3 [-1  -3  5] 3  6  7       5
    // 1  3  -1 [-3  5  3] 6  7       5
    // 1  3  -1  -3 [5  3  6] 7       6
    // 1  3  -1  -3  5 [3  6  7]      7
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1}, 1)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, -1}, 1)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{9, 11}, 2)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{4, -2}, 2)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {

        if (nums.length == 0) {
            return new int[]{};
        }

        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peekLast()]) {
                stack.removeLast();
            }
            stack.addLast(i);
        }

        int[] ans = new int[nums.length - k + 1];

        for (int i = 0; i < ans.length; i++) {
            while (!stack.isEmpty() && stack.peekFirst() < i) {
                stack.removeFirst();
            }

            ans[i] = nums[stack.peekFirst()];

            while (!stack.isEmpty() && i + k < nums.length && nums[i + k] > nums[stack.peekLast()]) {
                stack.removeLast();
            }

            stack.addLast(i + k);
        }

        return ans;
    }

}
