package com.zzzj.link;

/**
 * @author Zzzj
 * @create 2022-08-04 21:49
 */
public class Leet1670 {


    private static class FrontMiddleBackQueue {

        private static class Node {
            int val;
            Node prev;
            Node next;

            public Node(int val) {
                this.val = val;
            }
        }

        Node head;
        Node mid;
        Node tail;
        int size;

        public FrontMiddleBackQueue() {

        }

        public void pushFront(int val) {
            Node newNode = new Node(val);
            if (head == null) {
                head = mid = tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
                if (size < 2) {
                    head.next = tail;
                    mid = head;
                } else if (size > 3) {
                    mid = mid.prev;
                }
            }
            size++;
        }

        public void pushMiddle(int val) {
            if (size < 2) {
                pushFront(val);
            } else if (size == 2) {
                Node newNode = new Node(val);
                head.next = newNode;
                newNode.prev = head;
                newNode.next = tail;
                tail.prev = newNode;
            } else {
                Node newNode = new Node(val);
                Node prev = mid.prev;
                prev.next = newNode;
                newNode.prev = prev;

                newNode.next = mid;
                mid.prev = newNode;
                mid = newNode;
            }
            size++;
        }

        public void pushBack(int val) {
            Node newNode = new Node(val);
            if (tail == null) {
                head = mid = tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
                if (size < 2) {
                    newNode.prev = head;
                    mid = head;
                } else if (size > 3){

                }
            }
            size++;
        }

        public int popFront() {
            return -1;
        }

        public int popMiddle() {
            return -1;
        }

        public int popBack() {
            return -1;
        }

    }

}
