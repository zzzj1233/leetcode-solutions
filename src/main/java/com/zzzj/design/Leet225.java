package com.zzzj.design;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-05-12 15:04
 */
public class Leet225 {


    private static class MyStack {

        private Deque<Integer> deque;

        public MyStack() {
            deque = new LinkedList<>();
        }

        public void push(int x) {
            deque.addLast(x);
        }

        public int pop() {
            return deque.removeLast();
        }

        public int top() {
            return deque.peekLast();
        }

        public boolean empty() {
            return deque.isEmpty();
        }

    }

}
