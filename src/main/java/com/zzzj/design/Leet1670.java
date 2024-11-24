package com.zzzj.design;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.InvokableExp;
import com.zzzj.util.InvokeMethodSource;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leet1670 {

    public static void main(String[] args) {

        InvokableExp my = new InvokableExp(FrontMiddleBackQueue.class);
        InvokableExp right = new InvokableExp(Right.class);

        int N = 10;

        int limit = 10;

        InvokeMethodSource[] invokeMethodSources = {
                new InvokeMethodSource("pushFront", () -> new Object[]{LeetUtils.random.nextInt(limit) + 1}),
                new InvokeMethodSource("pushMiddle", () -> new Object[]{LeetUtils.random.nextInt(limit) + 1}),
                new InvokeMethodSource("pushBack", () -> new Object[]{LeetUtils.random.nextInt(limit) + 1}),
                new InvokeMethodSource("popFront"),
                new InvokeMethodSource("popMiddle"),
                new InvokeMethodSource("popBack")
        };

        while (true) {
            while (LeetUtils.executeExpression(my, right, N, invokeMethodSources)) {
                my = new InvokableExp(FrontMiddleBackQueue.class);
                right = new InvokableExp(Right.class);
            }
            System.out.println(my);
            System.out.println(my.getResults());
            System.out.println(right.getResults());

            my.reInvoke();
        }


//        System.out.println(my);
//        System.out.println(my.getResults());
//        System.out.println(right.getResults());
//
//        System.out.println(my.getResults().equals(right.getResults()));
    }

    private static class Node {
        Node prev;
        Node next;
        int val;

        public Node(int val) {
            this.val = val;
        }
    }

    private static class FrontMiddleBackQueue {

        Node head;
        Node tail;
        Node middle;

        int length;

        public FrontMiddleBackQueue() {

        }

        public void pushFront(int val) {
            Node node = new Node(val);
            length++;
            if (head == null) { // 0
                head = node;
                tail = node;
            } else if (head == tail) { // 1
                head = node;
                head.next = tail;
                tail.prev = head;
            } else {
                if (length == 3) {
                    middle = head;
                } else if (middle != null && length % 2 == 0) {
                    middle = middle.prev;
                }

                Node prevHead = head;
                head = node;

                head.next = prevHead;
                prevHead.prev = head;
            }
        }

        public void pushMiddle(int val) {
            // 没有节点 || 只有一个节点
            if (head == null || head == tail) {
                pushFront(val);
            } else if (middle == null) { // 有两个节点
                length++;
                Node node = new Node(val);

                head.next = node;
                node.prev = head;

                node.next = tail;
                tail.prev = node;
                middle = node;
            } else {
                length++;
                Node node = new Node(val);

                // 把节点放middle的前面
                if (length % 2 == 0) {
                    Node prev = middle.prev;
                    prev.next = node;
                    node.prev = prev;
                    node.next = middle;
                    middle.prev = node;
                } else {    // 把节点放在middle的后面
                    Node next = middle.next;
                    next.prev = node;
                    node.next = next;

                    node.prev = middle;
                    middle.next = node;
                }
                middle = node;
            }
        }

        public void pushBack(int val) {
            length++;
            Node node = new Node(val);
            if (tail == null) {
                head = node;
                tail = node;
            } else if (head == tail) {
                tail = node;
                tail.prev = head;
                head.next = tail;
            } else {
                // init middle
                if (length == 3) {
                    middle = tail;
                } else if (middle != null && length % 2 != 0) { // maintain middle
                    middle = middle.next;
                }

                Node prevTail = tail;

                tail = node;
                tail.prev = prevTail;
                prevTail.next = tail;

            }
        }

        public int popFront() {
            if (head == null) { // 没有元素
                return -1;
            }
            Node prevHead = head;
            if (head == tail) { // 一个元素
                head = null;
                tail = null;
            } else if (middle == null) { // 两个元素
                head = tail;
                head.next = null;
                head.prev = null;
            } else if (length == 3) { // 三个元素
                head = middle;
                middle = null;
                head.prev = null;
            } else { // > 3个元素
                // 维护head
                head = head.next;
                // 清空指针
                head.prev = null;
                prevHead.next = null;

                // 维护middle
                if (length % 2 == 0) {
                    middle = middle.prev;
                }
            }

            length--;
            return prevHead.val;
        }

        public int popMiddle() {
            if (head == null) {
                return -1;
            }
            if (head == tail) {
                int val = head.val;
                head = null;
                tail = null;
                length--;
                return val;
            } else if (middle == null) {
                return popFront();
            } else if (length == 3) {
                int val = middle.val;
                middle = null;
                head.next = tail;
                tail.prev = head;
                length--;
                return val;
            } else {
                Node prevMiddle = middle;
                middle.prev.next = middle.next;
                middle.next.prev = middle.prev;

                if (length % 2 != 0) {
                    middle = prevMiddle.prev;
                } else {
                    middle = prevMiddle.next;
                }
                length--;
                return prevMiddle.val;
            }
        }

        public int popBack() {
            if (tail == null) {
                return -1;
            }
            Node prevTail = tail;
            if (tail == head) { // 1个
                tail = null;
                head = null;
            } else if (middle == null) { // 两个
                tail = head;
                head.next = null;
                head.prev = null;
            } else if (length == 3) { // 三个
                tail = middle;
                tail.next = null;
                middle = null;
            } else { // > 3
                tail = tail.prev;
                tail.next = null;
                prevTail.prev = null;

                if (length % 2 != 0) {
                    middle = middle.prev;
                }
            }
            length--;
            return prevTail.val;
        }
    }

    private static class Right {
        Deque<Integer> q1;
        Deque<Integer> q2;
        //用size1，size2，维护两个队列的长度
        int size1, size2;

        public Right() {
            this.q1 = new ArrayDeque<>();
            this.q2 = new ArrayDeque<>();
            this.size1 = this.size2 = 0;
        }

        public void pushFront(int val) {
            q1.addFirst(val);
            size1++;
        }

        public void pushMiddle(int val) {
            //如果队列1长度 > 队列2长度，循环将队列1的最后一个元素移入队列2开头，直到size1 < size2;
            while (size1 > size2) {
                q2.addFirst(q1.pollLast());
                size1--;
                size2++;
            }
            //如果队列2长度 > 队列1长度 + 1,例如size1 = 2，size2 = 4，则循环将队列2的开头一个元素移入队列1末尾
            while (size2 > size1 + 1) {
                q1.addLast(q2.pollFirst());
                size2--;
                size1++;
            }
            q1.addLast(val);
            size1++;
        }

        public void pushBack(int val) {
            q2.addLast(val);
            size2++;
        }

        public int popFront() {
            //如果队列1长度 < 队列2长度，循环将队列2的第一个元素移入队列1末尾，直到size1 > size2;
            while (size2 > size1) {
                q1.addLast(q2.pollFirst());
                size2--;
                size1++;
            }
            if (size1 == 0) return -1;
            size1--;
            return q1.pollFirst();
        }

        public int popMiddle() {
            //如果队列1长度 > 队列2长度，循环将队列1的最后一个元素移入队列2开头，直到size1 < size2;
            while (size1 > size2) {
                q2.addFirst(q1.pollLast());
                size1--;
                size2++;
            }
            //如果队列2长度 > 队列1长度,例如size1 = 2，size2 = 3，则循环将队列2的开头一个元素移入队列1末尾
            while (size2 > size1) {
                q1.addLast(q2.pollFirst());
                size2--;
                size1++;
            }
            if (q1.isEmpty()) return -1;
            size1--;
            return q1.pollLast();
        }

        public int popBack() {
            //如果队列1长度 > 队列2长度，循环将队列1的最后一个元素移入队列2开头，直到size1 < size2;
            while (size1 > size2) {
                q2.addFirst(q1.pollLast());
                size1--;
                size2++;
            }
            if (q2.isEmpty()) return -1;
            size2--;
            return q2.pollLast();
        }
    }


}
