package com.zzzj.acw;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author zzzj
 * @create 2024-02-13 15:35
 */
public class Q253 {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {

        Scanner scanner = new Scanner("8\n" +
                "1 10\n" +
                "1 20\n" +
                "1 30\n" +
                "3 20\n" +
                "4 2\n" +
                "2 10\n" +
                "5 25\n" +
                "6 -1");

        int N = scanner.nextInt();

        while (N-- > 0) {
            int op = scanner.nextInt();
            int x = scanner.nextInt();

            // if (op == 1)

        }

    }

    private static class Node {

        Node left;
        Node right;
        int size;
        int cnt;
        // tree comparable entity
        int key;
        // heap comparable entity
        int val;

        public Node(int key) {
            this.key = key;
            val = RANDOM.nextInt();
            cnt = 1;
            size = 1;
        }

    }

    private static class Treap {

        Node root;

        public Treap() {
            Node node = new Node(Integer.MAX_VALUE);
            root = new Node(Integer.MIN_VALUE);
            root.right = node;
        }

        private static int size(Node node) {
            return node == null ? 0 : node.size;
        }

        // left rotate
        private Node lr(Node node) {
            Node l = node.left;
            node.left = l.right;
            l.right = node;
            return l;
        }

        // right rotate
        private Node rr(Node node) {
            Node r = node.right;
            node.right = r.left;
            r.left = node;
            return r;
        }

        public Node findByKey(int key) {
            return findByKey(root, key);
        }

        private Node findByKey(Node node, int key) {
            if (node == null || node.key == key)
                return node;
            if (node.key < key)
                return findByKey(node.right, key);
            else
                return findByKey(node.left, key);
        }

        private void pushup(Node node) {
            node.size = size(node.left) + size(node.right) + node.cnt;
        }

        public void insert(int key) {
            root = insert(root, key);
        }

        private Node insert(Node node, int key) {
            if (node == null)
                return new Node(key);

            if (node.key == key) {
                node.cnt++;
            } else if (node.key < key) {

                node.right = insert(node.right, key);

                if (node.val < node.right.val)
                    node = rr(node);

            } else {

                node.left = insert(node.left, key);

                if (node.val < node.left.val)
                    node = lr(node);

            }

            pushup(node);

            return node;
        }

        private Node remove(Node node, int key) {
            if (node == null)
                return null;
            if (node.key == key) {
                if (--node.cnt == 0) {
                    if (node.left == null)
                        return node.right;
                    if (node.right == null)
                        return node.left;

                    if (node.left.val > node.right.val) {
                        // node.left =
                    }
                }
            } else if (node.key < key) {
                node.right = remove(node.right, key);
            } else {
                node.left = remove(node.left, key);
            }

            return node;
        }

        public void remove(int key) {

        }

    }

}
