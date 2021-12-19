package com.zzzj.singlestack;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2021-12-12 10:38
 */
public class NearLess {

    // allow repeat num in nums<Array>

    //
    public static void main(String[] args) {
        // except answer
        // [-1, -1], [0, 6], [1, 5], [2, 4], [1, 5], [0, 6], [-1, -1], [6, -1]
        int[] nums = {1, 3, 4, 5, 4, 3, 1, 2};

        System.out.println(Arrays.deepToString(solution(nums)));

    }

    public static int[][] solution(int[] nums) {
        int[][] answer = new int[nums.length][2];

        LinkedList<LinkedList<Integer>> stack = new LinkedList<>();

        LinkedList<Integer> first = new LinkedList<>();
        first.add(0);
        stack.add(first);

        for (int i = 1; i < nums.length; i++) {

            int value = nums[i];

            while (!stack.isEmpty() && value <= nums[stack.peekLast().peekLast()]) {
                int lastVal = nums[stack.peekLast().peekLast()];
                if (value == lastVal) {
                    // 添加到链表末尾
                    stack.peekLast().addLast(i);
                    break;
                } else {
                    // value < lastValue
                    // 出栈且赋值
                    LinkedList<Integer> last = stack.removeLast();
                    int leftIdx = stack.isEmpty() ? -1 : stack.peekLast().peekLast();
                    for (Integer lastIdx : last) {
                        answer[lastIdx] = new int[]{leftIdx, i};
                    }
                }
            }

            LinkedList<Integer> list = new LinkedList<>();
            list.add(i);
            stack.add(list);
        }

        while (!stack.isEmpty()) {
            LinkedList<Integer> last = stack.removeLast();
            int leftIdx = stack.isEmpty() ? -1 : stack.peekLast().peekLast();
            for (Integer lastIdx : last) {
                answer[lastIdx] = new int[]{leftIdx, -1};
            }
        }

        return answer;
    }

}
