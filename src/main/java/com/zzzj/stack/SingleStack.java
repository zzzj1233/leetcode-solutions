package com.zzzj.stack;

import java.util.Stack;

/**
 * @author zzzj
 * @create 2021-12-01 12:15
 */
public class SingleStack {

    // 单调栈
    public static void main(String[] args) {
        // 73, 74, 75, 71, 69, 72, 76, 73

        // 76 73
        incrDemo();

        System.out.println("===========================");

        // 69 72 73
        descDemo();
    }

    private static void incrDemo() {
        int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};

        Stack<Integer> stack = new Stack<>();

        for (int num : nums) {
            while (!stack.isEmpty()) {
                Integer peek = stack.peek();
                if (peek < num) {
                    stack.pop();
                } else {
                    break;
                }
            }
            stack.add(num);
        }

        stack.forEach(System.out::println);
    }

    /**
     * 输入：[1,7,5,1,9,2,5,1]
     * 输出：[7,9,9,9,0,5,0,0]
     */
    private static void descDemo() {
        int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};

        Stack<Integer> stack = new Stack<>();

        for (int num : nums) {
            while (!stack.isEmpty()) {
                Integer peek = stack.peek();
                if (peek > num) {
                    stack.pop();
                } else {
                    break;
                }
            }
            stack.add(num);
        }

        stack.forEach(System.out::println);

    }

}
