package com.zzzj.link;


/**
 * @author zzzj
 * @create 2022-08-03 12:07
 */
public class Leet117 {

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }


    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node dummy = new Node();

        Node prev = dummy;

        Node cur = root;

        while (true) {
            if (cur.left == null && cur.right == null && cur.next == null && prev == dummy) {
                break;
            }
            if (cur.left != null){
                prev.next = cur.left;
                prev = prev.next;
            }
            if (cur.right != null){
                prev.next = cur.right;
                prev = prev.next;
            }
            if (cur.next != null) {
                cur = cur.next;
            } else {
                cur = dummy.next;
                dummy = new Node();
                prev = dummy;
            }
        }

        return root;
    }

}
