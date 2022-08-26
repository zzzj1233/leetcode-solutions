package com.zzzj.design;

/**
 * @author zzzj
 * @create 2022-08-24 18:26
 */
public class Leet1756 {


    public static void main(String[] args) {
        MRUQueue queue = new MRUQueue(8);

        System.out.println(queue.fetch(3));
        System.out.println(queue.fetch(5));
        System.out.println(queue.fetch(2));
        System.out.println(queue.fetch(8));
    }

    private static class MRUQueue {

        Node head;
        Node tail;

        Node[] nodes;

        public MRUQueue(int n) {
            nodes = new Node[n + 1];

            head = new Node(1);

            if (n == 1) {
                tail = head;
                return;
            }

            Node prev = head;
            nodes[1] = head;

            for (int i = 2; i < n; i++) {
                Node node = new Node(i);
                prev.next = node;
                node.prev = prev;
                prev = node;
                nodes[i] = node;
            }

            tail = new Node(n);
            tail.prev = prev;
            prev.next = tail;
            nodes[n] = tail;
        }

        public int fetch(int k) {
            Node node = nodes[k];

            if (node != tail) {
                if (node == head) {
                    head = node.next;
                    head.prev = null;

                    tail.next = node;
                    node.prev = tail;
                    tail = node;
                } else {
                    Node prev = node.prev;
                    prev.next = node.next;
                    node.next.prev = prev;

                    tail.next = node;
                    node.prev = tail;
                    tail = node;
                }
            }

            return tail.val;
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
