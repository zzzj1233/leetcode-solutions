package com.zzzj.design;

import java.util.Stack;

/**
 * @author zzzj
 * @create 2023-05-06 16:14
 */
public class Leet2960 {

    public static void main(String[] args) {
        SortedStack stack = new SortedStack();

        stack.push(5);
        stack.push(1);

        System.out.println(stack.peek());

        stack.push(4);

        System.out.println(stack.peek());

        stack.push(3);

        System.out.println(stack.peek());

        stack.pop();

        System.out.println(stack.peek());

        stack.pop();

        System.out.println(stack.peek());

        stack.pop();

        System.out.println(stack.peek());
    }


    private static class SortedStack {

        private final Stack<Integer> s1;

        private final Stack<Integer> s2;

        // 最多只能使用一个临时栈保存数据
        // 最小的元素位于栈顶
        public SortedStack() {
            // 使s1保持有序
            this.s1 = new Stack<>();
            this.s2 = new Stack<>();
        }

        public void push(int val) {
            // val = 4

            // s2 = 1
            // s1 = 5
            while (!s1.isEmpty() && val > s1.peek()) {
                s2.push(s1.pop());
            }

            // s2 = 1
            // s1 = 5 4
            s1.add(val);

            // s2 = []
            // s1 = 5 4 1
            while (!s2.isEmpty()) {
                s1.add(s2.pop());
            }
        }

        public void pop() {
            if (isEmpty()) {
                return;
            }
            s1.pop();
        }

        public int peek() {
            if (isEmpty()) {
                return -1;
            }
            return this.s1.peek();
        }

        public boolean isEmpty() {
            return s1.isEmpty();
        }

    }
}
