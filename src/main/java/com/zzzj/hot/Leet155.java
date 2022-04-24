package com.zzzj.hot;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-04-19 16:28
 */
public class Leet155 {

    static class MinStack {

        LinkedList<Integer> stack = new LinkedList<>();
        LinkedList<Integer> minStack = new LinkedList<>();

        public MinStack() {

        }

        public void push(int val) {
            stack.addLast(val);
            if (minStack.isEmpty()) {
                minStack.addLast(val);
            } else {
                minStack.addLast(val > minStack.peekLast() ? minStack.peekLast() : val);
            }
        }

        public void pop() {
            stack.removeLast();
            minStack.removeLast();
        }

        public int top() {
            return stack.peekLast();
        }

        public int getMin() {
            return minStack.peekLast();
        }

    }


}
