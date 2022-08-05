package com.zzzj.link;


/**
 * @author zzzj
 * @create 2021-12-03 17:21
 */
public class Leet430 {

    private static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
//        Node node4 = new Node(4);
//        Node node5 = new Node(5);
//        Node node6 = new Node(6);
//        Node node7 = new Node(7);
//        Node node8 = new Node(8);
//        Node node9 = new Node(9);

        node1.child = node2;
        node2.child = node3;
//        node3.child = node4;
//        node4.next = node5;
//        node4.child = node6;
//        node6.next = node7;
//        node7.next = node8;
//        node3.next = node9;

        Node node = flatten(node1);

        Node cur = node;

        System.out.println("~");
    }

    public static Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        flatten0(head);
        return head;
    }

    private static Node flatten0(Node node) {
        Node cur = node;
        Node prev = null;

        Node last = null;

        boolean hasNext = false;

        while (cur != null) {
            Node next = cur.next;
            if (cur.child != null) {
                cur.next = cur.child;
                hasNext = next != null;
                cur.child.prev = cur;
                last = flatten0(cur.child);
                cur.child = null;
                last.next = next;
                if (next != null) {
                    next.prev = last;
                }
            }
            prev = cur;
            cur = next;
        }
        if (last != null) {
            if (hasNext) {
                return prev;
            }
            return last;
        } else {
            return prev;
        }
    }

}
