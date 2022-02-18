package com.zzzj.tree;

import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-06-04 15:02
 */
public class AvlTree {

    private Node head;
    private int size;

    public void add(int e) {
        this.head = this.add(this.head, e);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        node.height = Math.max(leftHeight, rightHeight) + 1;
        return node.height;
    }

    private int getLeftHeight(Node node) {
        if (node.left == null) {
            return 0;
        }
        return node.left.height;
    }

    private int getRightHeight(Node node) {
        if (node.right == null) {
            return 0;
        }
        return node.right.height;
    }

    private Node ll(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        return x;
    }

    private Node rr(Node y) {
        Node x = y.right;
        y.right = x.left;
        x.left = y;
        return x;
    }

    private Node lr(Node node) {
        node.left = rr(node.left);
        return ll(node);
    }

    private Node rl(Node node) {
        node.right = ll(node.right);
        return rr(node);
    }

    private Node add(Node node, int e) {
        if (node == null) {
            return new Node(e);
        }
        if (node.value < e) {
            node.right = add(node.right, e);
        } else if (node.value > e) {
            node.left = add(node.left, e);
        } else {
            return node;
        }

        getHeight(node);

        if ((getLeftHeight(node) - getRightHeight(node)) > 1) {
            if (getLeftHeight(node.left) > getRightHeight(node.left)) {
                node = ll(node);
            } else {
                node = lr(node);
            }
            getHeight(node);
        } else if ((getRightHeight(node) - getLeftHeight(node)) > 1) {
            if (getRightHeight(node.right) > getLeftHeight(node.right)) {
                node = rr(node);
            } else {
                node = rl(node);
            }
            getHeight(node);
        }

        return node;
    }

    public void remove(int e) {
        this.head = this.remove(this.head, e);
    }

    private Node remove(Node node, int e) {
        if (node == null) {
            return null;
        }
        // 要删除的元素
        if (node.value == e) {
            if (node.left != null && node.right == null) {
                return node.left;
            } else if (node.left == null && node.right != null) {
                return node.right;
            } else if (node.left == null && node.right == null) {
                return null;
                // node.left != null && node.right != null
            } else {

            }
        } else if (node.value < e) {
            node.right = remove(node.right, e);
        } else {
            node.left = remove(node.left, e);
        }

        return null;
    }

    public static class Node {
        private int value;
        private Node left;
        private Node right;
        private int height;

        public Node(int value) {
            this.value = value;
        }

    }

    public static void main(String[] args) {
        AvlTree tree = new AvlTree();

        List<Integer> list = Arrays.asList(70, 22, 2, 19, 78, 49, 26, 50, 45, 13, 79, 74);

        list.forEach(tree::add);

        System.out.println("");
    }

}
