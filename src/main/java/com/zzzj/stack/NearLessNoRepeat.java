package com.zzzj.stack;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2021-12-12 10:23
 */
public class NearLessNoRepeat {

    // 给定一个数组,找到它左边最小的元素索引和右边最小的元素索引
    // eg. 1,3,4,2 = [-1, -1] , [0, 3], [1, 3] , [0, -1]

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(solution(new int[]{1, 3, 4, 2})));
    }

    public static int[][] solution(int[] nums) {
        int[][] answer = new int[nums.length][2];

        LinkedList<Integer> stack = new LinkedList<>();
        stack.add(0);

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            if (!stack.isEmpty() && num <= nums[stack.peekLast()]) {
                // 出栈
                // 下面的元素出栈
                while (!stack.isEmpty() && num < nums[stack.peekLast()]) {
                    Integer last = stack.removeLast();
                    int leftLess = stack.isEmpty() ? -1 : stack.peekLast();
                    answer[last] = new int[]{leftLess, i};
                }
            }

            stack.addLast(i);
        }

        while (!stack.isEmpty()) {
            Integer last = stack.removeLast();
            int leftLess = stack.isEmpty() ? -1 : stack.peekLast();
            answer[last] = new int[]{leftLess, -1};
        }

        return answer;
    }

}
