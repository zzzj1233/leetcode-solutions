package com.zzzj.window;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2021-12-24 17:18
 */
public class Leet239 {

    /**
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     */

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{4, -2}, 2)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {

        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(0);

        for (int i = 1; i < k; i++) {
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.removeLast();
            }
            queue.add(i);
        }

        int[] ans = new int[nums.length - k + 1];
        ans[0] = nums[queue.peekFirst()];

        for (int i = k; i < nums.length; i++) {
            while (!queue.isEmpty() && queue.peekFirst() < i - k + 1) {
                queue.removeFirst();
            }
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.removeLast();
            }
            queue.add(i);
            ans[i - k + 1] = nums[queue.peekFirst()];
        }

        return ans;
    }

}
