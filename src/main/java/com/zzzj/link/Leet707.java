package com.zzzj.link;


/**
 * @author zzzj
 * @create 2022-08-22 11:54
 */
public class Leet707 {

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(3);
        list.addAtHead(2);
        list.addAtHead(1);

        System.out.println(list.get(2));
    }

    private static class MyLinkedList {

        private Node head;

        private Node tail;

        int size;

        public MyLinkedList() {

        }

        public int get(int index) {
            Node node = get0(index);
            return node == null ? -1 : node.val;
        }

        public void addAtHead(int val) {
            if (head == null) {
                head = new Node(val);
                tail = head;
            } else {
                if (head.next == null) {
                    Node oldHead = head;
                    head = new Node(val);
                    head.next = oldHead;
                    oldHead.prev = head;
                    tail = oldHead;
                } else {
                    Node oldHead = head;
                    head = new Node(val);
                    head.next = oldHead;
                    oldHead.prev = head;
                }
            }
            size++;
        }

        public void addAtTail(int val) {
            if (tail == null) {
                tail = new Node(val);
                head = tail;
            } else {
                if (tail.prev == null) {
                    Node oldTail = tail;
                    tail = new Node(val);
                    tail.prev = oldTail;
                    oldTail.next = tail;
                    head = oldTail;
                } else {
                    Node oldTail = tail;
                    tail = new Node(val);
                    tail.prev = oldTail;
                    oldTail.next = tail;
                }
            }
            size++;
        }

        private Node get0(int index) {
            Node cur = head;
            int count = index;
            while (cur != null && count > 0) {
                cur = cur.next;
                count--;
            }
            return cur;
        }

        public void addAtIndex(int index, int val) {
            if (index <= 0) {
                addAtHead(val);
            } else if (index == size) {
                addAtTail(val);
            } else if (index < size) {
                Node newNode = new Node(val);
                Node node = get0(index);
                Node prev = node.prev;
                prev.next = newNode;
                newNode.prev = prev;
                newNode.next = node;
                node.prev = newNode;
                size++;
            }
        }

        public void deleteAtIndex(int index) {
            if (index <= 0) {
                head = head.next;
                if (head != null) {
                    head.prev = null;
                }
                size--;
            } else if (index == size || index == size - 1) {
                tail = tail.prev;
                if (tail != null) {
                    tail.next = null;
                }
                size--;
            } else if (index < size) {
                Node node = get0(index);
                Node prev = node.prev;
                prev.next = node.next;
                node.next.prev = prev;
                size--;
            }
        }

        private static class Node {
            Node prev;
            Node next;
            int val;

            public Node(int val) {
                this.val = val;
            }

        }

    }


}
