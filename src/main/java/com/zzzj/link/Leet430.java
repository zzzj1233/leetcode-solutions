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
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);

        node1.next = node2;
        node2.next = node3;
        node3.child = node4;
        node4.next = node5;
        node4.child = node6;
        node6.next = node7;
        node7.next = node8;
        node3.next = node9;

        Node node = flatten(node1);

        System.out.println("~");
    }

    public static Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        last = null;
        last = head;
        return flatten(head, null);
    }

    private static Node last = null;

    public static Node flatten(Node head, Node next) {
        Node answer = head;

        Node originNext = answer.next;

        if (answer.child != null) {
            answer.next = flatten(answer.child, originNext);
        }

        if (originNext != null) {
            last.next = flatten(originNext, next);
        }

        if (originNext == null && next != null) {
            answer.next = next;
        }

        last = answer;

        return answer;
    }

}
