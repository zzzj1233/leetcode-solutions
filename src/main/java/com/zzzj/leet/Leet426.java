package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-18 11:13
 */
public class Leet426 {

    public static void main(String[] args) {

    }

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

    private static Node lastVisited;

    private static Node firstNode;

    public static Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        inOrder(root);
        if (firstNode != lastVisited) {
            firstNode.left = lastVisited;
            lastVisited.right = firstNode;
        }
        return firstNode;
    }

    public static void inOrder(Node root) {
        // 1. 记录链表中的第一个节点
        if (root.left == null && root.right == null) {
            if (firstNode == null) {
                firstNode = root;
            }
        }

        if (root.left != null) {
            inOrder(root.left);
        }

        if (lastVisited != null) {
            lastVisited.right = root;
            root.left = lastVisited;
        }

        lastVisited = root;

        if (root.right != null) {
            inOrder(root.right);
        }

    }

}
