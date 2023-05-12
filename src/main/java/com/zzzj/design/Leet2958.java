package com.zzzj.design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author zzzj
 * @create 2023-05-06 17:41
 */
public class Leet2958 {

    public static void main(String[] args) {
        StackOfPlates stack = new StackOfPlates(2);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.popAt(0));
        System.out.println(stack.popAt(0));
        System.out.println(stack.popAt(0));

    }

    private static class StackOfPlates {

        private final int cap;

        private final LinkedList<Stack<Integer>> list;

        public StackOfPlates(int cap) {
            list = new LinkedList<>();
            this.cap = cap;
        }

        public void push(int val) {
            if (cap == 0) {
                return;
            }
            if (list.isEmpty() || list.peekLast().size() == cap) {
                list.add(new Stack<>());
            }
            list.peekLast().add(val);
        }

        public int pop() {
            if (list.isEmpty() || cap == 0) {
                return -1;
            }
            Integer value = list.peekLast().pop();
            if (list.peekLast().isEmpty()) {
                list.removeLast();
            }
            return value;
        }

        public int popAt(int index) {
            if (index >= list.size() || cap == 0) {
                return -1;
            }

            Iterator<Stack<Integer>> it = list.iterator();

            int cur = 0;

            int val = 0;

            while (it.hasNext()) {
                Stack<Integer> next = it.next();
                if (cur == index) {
                    val = next.pop();
                    if (next.isEmpty()) {
                        it.remove();
                    }
                    break;
                }
                cur++;
            }

            return val;
        }
    }

}
