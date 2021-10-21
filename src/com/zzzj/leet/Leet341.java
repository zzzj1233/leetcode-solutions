package com.zzzj.leet;

import java.util.*;

public class Leet341 {

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    public static class NestedIntegerImpl implements NestedInteger {

        public int number;

        public List<NestedInteger> list;

        public boolean isNumber;

        public NestedIntegerImpl() {
        }

        public NestedIntegerImpl(int number, boolean flag) {
            this.list = new ArrayList<>(1);
            this.list.add(new NestedIntegerImpl(number));
        }

        public NestedIntegerImpl(int number) {
            this.isNumber = true;
            this.number = number;
        }

        public NestedIntegerImpl(int... numbers) {
            this.isNumber = false;
            this.list = new ArrayList<>(numbers.length);
            for (int i : numbers) {
                this.list.add(new NestedIntegerImpl(i));
            }
        }

        @Override
        public boolean isInteger() {
            return isNumber;
        }

        @Override
        public Integer getInteger() {
            return number;
        }

        @Override
        public List<NestedInteger> getList() {
            return list;
        }

        @Override
        public String toString() {
            return String.valueOf(this.number);
        }
    }

    public static void main(String[] args) {
        NestedIntegerImpl nestedInteger1 = new NestedIntegerImpl(1);
        NestedIntegerImpl nestedInteger2 = new NestedIntegerImpl();

        nestedInteger2.isNumber = false;
        nestedInteger2.list = Arrays.asList(new NestedIntegerImpl(4), new NestedIntegerImpl(6, false));

        NestedIterator nestedIterator = new NestedIterator(Arrays.asList(nestedInteger1, nestedInteger2));

        while (nestedIterator.hasNext()) {
            System.out.println(nestedIterator.next());
        }
    }

    public static class NestedIterator implements Iterator<Integer> {

        private LinkedList<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new LinkedList<>();

            init(nestedList);
        }

        public void init(List<NestedInteger> nestedList) {
            for (NestedInteger nestedInteger : nestedList) {
                if (nestedInteger.isInteger()) {
                    stack.add(nestedInteger);
                } else {
                    init(nestedInteger.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return stack.removeFirst().getInteger();
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

    }

}
