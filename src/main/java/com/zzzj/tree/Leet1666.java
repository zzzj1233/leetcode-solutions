package com.zzzj.tree;

import cn.hutool.core.util.StrUtil;

/**
 * @author zzzj
 * @create 2022-08-09 18:13
 */
public class Leet1666 {

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    public static void main(String[] args) {
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        setLeft(node3, node5);
        setRight(node3, node1);

        setLeft(node5, node6);
        setRight(node5, node2);

        setLeft(node1, node0);
        setRight(node1, node8);

        setLeft(node2, node7);
        setRight(node2, node4);

        flipBinaryTree(node3, node7);

        System.out.println("~");
        // printTree(node7);
    }

    public static void setLeft(Node root, Node left) {
        root.left = left;
        left.parent = root;
    }

    public static void setRight(Node root, Node right) {
        root.right = right;
        right.parent = root;
    }

    public static void printTree(Node root) {
        System.out.println(StrUtil.format("val = {} , left = {} , right = {} , parent = {}", root, root.left, root.right, root.parent));

        if (root.left != null) {
            printTree(root.left);
        }

        if (root.right != null) {
            printTree(root.right);
        }
    }

    public static Node flipBinaryTree(Node root, Node leaf) {

        // parent -> left
        // left -> right

        Node node = leaf;

        Node preParent = null;

        while (node != root) {
            Node left = node.left;
            Node parent = node.parent;
            if (left != null) {
                node.right = left;
            }
            if (parent != null) {
                node.left = parent;

                if (node == parent.left) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            node.parent = preParent;
            preParent = node;
            node = parent;
        }

        node.parent = preParent;

        return leaf;
    }

}
