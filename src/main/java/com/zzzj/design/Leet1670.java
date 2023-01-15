package com.zzzj.design;

import com.zzzj.util.InvokableExp;
import com.zzzj.util.LeetCodeExp;

public class Leet1670 {

    public static void main(String[] args) {
        LeetCodeExp exp = new InvokableExp(FrontMiddleBackQueue.class);

        System.out.println(exp.invokeMethod("pushFront", 1));
        System.out.println(exp.invokeMethod("pushFront", 2));
        System.out.println(exp.invokeMethod("pushFront", 3));
        System.out.println(exp.invokeMethod("pushFront", 4));
        System.out.println(exp.invokeMethod("popMiddle"));
        System.out.println(exp.invokeMethod("popMiddle"));
        System.out.println(exp.invokeMethod("popMiddle"));
        System.out.println(exp.invokeMethod("popMiddle"));

        System.out.println(exp);
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
            if (head == null) {
                head = node;
                tail = node;
            } else if (head == tail) {
                head = node;
                head.next = tail;
                tail.prev = head;
            } else {
                if (middle == null) {
                    middle = head;
                }
                Node prevHead = head;
                head = node;

                head.next = prevHead;
                prevHead.prev = head;
            }
        }

        public void pushMiddle(int val) {
            // 没有节点 || 只有一个节点 || 只有两个节点
            if (head == null || head == tail || middle == null) {
                pushFront(val);
            } else {
                length++;
                Node node = new Node(val);

                Node prevNext = middle.next;
                middle.next = node;
                node.prev = middle;

                node.next = prevNext;

                if (length % 2 != 0) {
                    middle = middle.next;
                }
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
                Node prevTail = tail;

                if (middle == null) {
                    middle = tail;
                }

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
                if (length % 2 != 0) {
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

}
