package com.zzzj.link;

import com.sun.jmx.snmp.SnmpBadSecurityLevelException;

/**
 * @author zzzj
 * @create 2021-11-29 18:00
 */
public class LeetOffer36 {

    private static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    private static Node answer;

    private static Node cur;

    private static Node pre;

    public static Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        answer = null;

        inOrder(root);

        return answer;
    }

    private static void inOrder(Node root) {
        if (root.left != null) {
            inOrder(root.left);
        } else if (answer == null) {
            answer = root;
        }

        pre = cur;

        cur = root;

        if (pre != null) {
            pre.right = cur;
        }

        cur.left = pre;

        if (root.right != null) {
            inOrder(root.right);
        }

        if (root.left == null && root.right == null) {
            root.right = answer;
            answer.left = root;
        }

    }

}
