package com.zzzj.link;

import java.util.Objects;
import java.util.TreeSet;

public class Leet716 {


    private static class MaxStack {

        static int globalId;

        static {
            globalId = 0;
        }

        static class Node {
            int val;

            int id = globalId++;
            Node next;
            Node prev;

            public Node(int val) {
                this.val = val;
            }

            public Node(int val, Node next) {
                this.val = val;
                this.next = next;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node node = (Node) o;
                return val == node.val && id == node.id && Objects.equals(next, node.next);
            }

            @Override
            public int hashCode() {
                return Objects.hash(val, id, next);
            }
        }

        TreeSet<Node> maxStack = new TreeSet<>((o1, o2) -> {
            if (o2.val == o1.val) {
                return o2.id - o1.id;
            }
            return o2.val - o1.val;
        });

        Node tail;
        Node head;

        public MaxStack() {

        }

        public void push(int x) {
            Node node = new Node(x);
            if (head == null) {
                head = node;
                tail = head;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            maxStack.add(node);
        }

        public int pop() {
            Node top = tail;
            if (top == head) {
                head = null;
                tail = null;
            } else {
                Node prev = top.prev;
                prev.next = null;
                tail = prev;
            }

            maxStack.remove(top);

            return top.val;
        }

        public int top() {
            return tail.val;
        }

        public int peekMax() {
            return maxStack.first().val;
        }

        public int popMax() {
            Node top = maxStack.pollFirst();

            Node prev = top.prev;
            Node next = top.next;

            if (prev != null) {
                prev.next = next;
            } else {
                head = next;
            }

            if (next != null) {
                next.prev = prev;
            } else {
                tail = prev;
            }

            return top.val;
        }
    }


}
