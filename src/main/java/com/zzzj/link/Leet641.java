package com.zzzj.link;

/**
 * @author zzzj
 * @create 2022-12-05 17:10
 */
public class Leet641 {


    private static class MyCircularDeque {

        final int capacity;

        final LinkList list = new LinkList();

        int size;

        public MyCircularDeque(int k) {
            capacity = k;
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }

            list.addHead(value);
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }

            list.addTail(value);
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }

            list.removeHead();
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }

            list.removeTail();
            size--;
            return true;
        }

        public int getFront() {
            if (isEmpty()){
                return -1;
            }
            return list.head.value;
        }

        public int getRear() {
            if (isEmpty()){
                return -1;
            }
            return list.tail.value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }


        private static class LinkList {
            Node head;
            Node tail;

            public void addHead(int value) {
                if (head == null) {
                    head = new Node(value);
                    tail = head;
                } else {
                    Node node = new Node(value);

                    node.next = head;
                    head.prev = node;

                    head = node;
                }
            }

            public void addTail(int value) {
                if (tail == null) {
                    tail = new Node(value);
                    head = tail;
                } else {
                    Node node = new Node(value);

                    node.prev = tail;
                    tail.next = node;

                    tail = node;
                }
            }

            public int removeHead() {
                Node head = this.head;

                Node next = this.head.next;

                if (next != null) {
                    next.prev = null;
                } else {
                    tail = null;
                }

                this.head = next;

                return head.value;
            }

            public int removeTail() {
                Node tail = this.tail;

                Node prev = tail.prev;

                if (prev != null) {
                    prev.next = null;
                } else {
                    head = null;
                }

                this.tail = prev;

                return tail.value;
            }
        }

        private static class Node {
            int value;
            Node prev;
            Node next;

            public Node(int value) {
                this.value = value;
            }

        }

    }


}
