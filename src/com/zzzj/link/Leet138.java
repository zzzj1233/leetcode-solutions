package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-26 18:08
 */
public class Leet138 {

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

        Node cur = head;

        while (cur != null) {
            Node next = cur.next;
            Node copy = new Node(cur.val);
            cur.next = copy;
            copy.next = next;
            cur = next;
        }

        cur = head;

        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }

        // copy
        cur = head;

        while (cur != null) {
            if (cur.next == null) {
                break;
            }
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // unlink
        cur = head;

        while (cur != null) {
            if (cur.next == null) {
                break;
            }
            Node next = cur.next;
            cur.next = cur.next.next;
            cur = next;
        }

        return head.next;
    }

}
