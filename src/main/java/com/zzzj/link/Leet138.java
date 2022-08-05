package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-26 18:08
 */
public class Leet138 {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node13 = new Node(13);
        Node node7 = new Node(7);

        node7.next = node13;
        node13.next = node11;
        node11.next = node10;
        node10.next = node1;

        node13.random = node7;
        node11.random = node1;
        node10.random = node11;
        node1.random = node7;

        System.out.println(copyRandomList(node7));
    }

    private static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node node = head;

        while (node != null) {
            Node next = node.next;

            Node newNode = new Node(node.val);
            newNode.next = next;
            newNode.random = node.random;

            node.next = newNode;
            node = next;
        }

        node = head;

        Node ans = head.next;

        while (node != null) {
            if (node.next.random != null) {
                node.next.random = node.next.random.next;
            }
            node = node.next.next;
        }

        // 还原原链表
        node = head;

        while (node != null) {
            Node next = node.next;
            if (next != null) {
                node.next = next.next;
            }
            node = next;
        }

        return ans;
    }

}
