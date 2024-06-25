package com.zzzj.design;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2024-06-25 15:16
 */
public class Leet1172 {

    public static void main(String[] args) {

        DinnerPlates dinnerPlates = new DinnerPlates(2);

        System.out.println(LeetUtils.executeExpression("[\"DinnerPlates\",\"push\",\"push\",\"push\",\"popAtStack\",\"pop\",\"pop\"]", "[[1],[1],[2],[3],[1],[],[]]", dinnerPlates));

    }

    private static class DinnerPlates {

        private final int capacity;

        private final ArrayList<LinkedList<Integer>> stacks = new ArrayList<>();

        private final TreeSet<Integer> freeList;

        public DinnerPlates(int capacity) {
            this.capacity = capacity;
            freeList = new TreeSet<>();
        }

        public LinkedList<Integer> peekLast() {
            return stacks.get(stacks.size() - 1);
        }

        public void removeLast() {
            stacks.remove(stacks.size() - 1);
        }

        public void push(int val) {
            if (freeList.isEmpty()) {
                stacks.add(new LinkedList<>());
                freeList.add(stacks.size() - 1);
            }

            Integer free = freeList.first();

            LinkedList<Integer> s = stacks.get(free);

            s.add(val);

            if (s.size() == capacity) {
                freeList.pollFirst();
            }


        }

        public int pop() {
            if (stacks.isEmpty())
                return -1;

            LinkedList<Integer> last = peekLast();

            if (last.isEmpty()) {
                removeLast();
                freeList.remove(stacks.size());
                return pop();
            }

            Integer v = last.removeLast();

            if (last.isEmpty()) {
                freeList.remove(stacks.size() - 1);
                removeLast();
            } else {
                freeList.add(stacks.size() - 1);
            }

            return v;
        }

        public int popAtStack(int index) {
            if (index < 0 || index >= stacks.size())
                return -1;

            LinkedList<Integer> s = stacks.get(index);

            if (s.isEmpty())
                return -1;

            Integer v = s.removeLast();

            freeList.add(index);

            if (s.isEmpty() && index == stacks.size() - 1) {
                removeLast();
                freeList.remove(index);
            }

            return v;
        }

    }

}
