package com.zzzj.design;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-05-11 17:03
 */
public class Leet2823 {

    private static class MaxQueue {

        private final Deque<Integer> deque;

        private final Deque<Integer> maxQueue;


        public MaxQueue() {
            deque = new LinkedList<>();
            maxQueue = new LinkedList<>();
        }

        public int max_value() {
            return deque.isEmpty() ? -1 : maxQueue.peekFirst();
        }

        public void push_back(int value) {
            deque.addLast(value);

            while (!maxQueue.isEmpty() && value > maxQueue.peekLast()) {
                maxQueue.removeLast();
            }

            maxQueue.addLast(value);
        }

        public int pop_front() {
            if (deque.isEmpty()) {
                return -1;
            }

            if (maxQueue.peekFirst().equals(deque.peekFirst())){
                maxQueue.removeFirst();
            }

            return deque.removeFirst();
        }

    }

}
