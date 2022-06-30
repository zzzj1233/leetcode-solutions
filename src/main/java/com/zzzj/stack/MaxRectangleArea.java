package com.zzzj.stack;

import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2021-12-12 22:12
 */
public class MaxRectangleArea {

    public static void main(String[] args) {
        // 10
        System.out.println(solution(new int[]{3, 3, 2, 1}));
    }


    public static int solution(int[] nums) {

        int n = nums.length;

        LinkedList<Integer> stack = new LinkedList<>();

        stack.add(0);

        int answer = -1;

        for (int i = 1; i < n; i++) {
            int value = nums[i];
            while (!stack.isEmpty() && value <= nums[stack.peekLast()]) {
                Integer last = stack.removeLast();
                // 计算last的最大值
                int lastVal = nums[last];
                answer = Math.max(answer, lastVal * (stack.isEmpty() ? last + 1 : last - stack.peekLast()));
            }
            stack.add(i);
        }

        while (!stack.isEmpty()) {
            Integer last = stack.removeLast();
            int lastVal = nums[last];
            answer = Math.max(answer, lastVal * (stack.isEmpty() ? n : n - stack.peekLast() - 1));
        }

        return answer;
    }

}
