package com.zzzj.design;

import java.util.Stack;

/**
 * @author zzzj
 * @create 2023-01-13 14:12
 */
public class Leet232 {


    private static class MyQueue {

        private Stack<Integer> s1;

        private Stack<Integer> s2;

        public MyQueue() {
            s1 = new Stack<>();
            s2 = new Stack<>();
        }

        public void push(int x) {
            while (!s2.isEmpty()) {
                s1.add(s2.pop());
            }
            s1.add(x);
        }

        public int pop() {
            while (!s1.isEmpty()) {
                s2.add(s1.pop());
            }
            return s2.pop();
        }

        public int peek() {
            while (!s1.isEmpty()) {
                s2.add(s1.pop());
            }
            return s2.peek();
        }

        public boolean empty() {
            return s1.isEmpty() && s2.isEmpty();
        }
    }

}
