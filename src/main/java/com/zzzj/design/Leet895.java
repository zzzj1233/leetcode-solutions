package com.zzzj.design;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-10-25 11:24
 */
public class Leet895 {

    public static void main(String[] args) {

        FreqStack stack = new FreqStack();

        stack.push(5);
        stack.push(7);
        stack.push(5);
        stack.push(7);
        stack.push(4);
        stack.push(5);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    private static class FreqStack {

        Map<Integer, Integer> freq;

        TreeMap<Integer, LinkedList<Integer>> queue;

        public FreqStack() {
            freq = new HashMap<>();
            queue = new TreeMap<>();
        }

        public void push(int val) {

            int newCnt = freq.getOrDefault(val, 0) + 1;

            freq.put(val, newCnt);

            queue.computeIfAbsent(newCnt, integer -> new LinkedList<>()).addLast(val);
        }

        public int pop() {
            if (queue.isEmpty())
                return -1;

            Map.Entry<Integer, LinkedList<Integer>> last = queue.lastEntry();

            LinkedList<Integer> q = last.getValue();

            Integer result = q.removeLast();

            if (q.isEmpty())
                queue.remove(last.getKey());

            int newCnt = freq.get(result) - 1;

            freq.put(result, newCnt);

            return result;
        }

    }
}
