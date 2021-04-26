package com.zzzj.tree;

import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2021-04-24 17:25
 */
public class Bst {

    private int size;
    private Node head;

    public void insert(int key, int value) {
        this.head = insert(head, key, value);
    }

    private Node insert(Node node, int key, int value) {
        if (node == null) {
            this.size++;
            return new Node(key, value);
        }
        if (node.key == key) {
            node.value = value;
            return node;
        } else if (node.key < key) {
            node.right = insert(node.right, key, value);
            return node;
        } else {
            node.left = insert(node.left, key, value);
            return node;
        }
    }

    private boolean contains(int key) {
        return search(this.head, key) != null;
    }

    public int search(int key) {
        Node search = search(this.head, key);
        if (search == null) {
            return -1;
        }
        return search.value;
    }

    public void levelOrder() {
        if (this.head == null) {
            return;
        }

        LinkedList<Node> stack = new LinkedList<>();
        stack.add(this.head);

        while (!stack.isEmpty()) {
            Node pop = stack.removeFirst();
            System.out.println(pop.key);
            if (pop.left != null) {
                stack.addLast(pop.left);
            }
            if (pop.right != null) {
                stack.addLast(pop.right);
            }
        }
    }

    public void remove(int key) {
        this.head = remove(this.head, key);
    }

    /**
     * 思想
     * 1. 如果当前被删除的节点没有左节点,那么直接返回该节点的右节点
     * 2. 和1相反
     * 3. 如果既有左节点又有右节点,那么找到 [右节点] 中 [最小] 的一个节点,替换当节点
     * 因为这个节点一定满足  > 当前节点的所有左节点 && < 当前节点的所有右节点
     */
    private Node remove(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (node.key == key) {
            size--;
            // 1. 情况1
            if (node.left == null) {
                return node.right;
                // 2. 情况2
            } else if (node.right == null) {
                return node.left;
                // 3. 情况3
            } else {
                // 3.1 找到当前节点的右节点中最小的一个元素
                Node min = findMin(node.right);
                // 3.2 从右子树中删除这个元素
                size++;
                min.right = removeMin(node.right);
                // 3.3 返回这个元素作为递归的结果
                min.left = node.left;
                return min;
            }
        } else if (node.key < key) {
            node.right = remove(node.right, key);
            return node;
        } else {
            node.left = remove(node.left, key);
            return node;
        }
    }

    public Node removeMin() {
        return removeMin(this.head);
    }

    public Node removeMax() {
        return removeMax(this.head);
    }

    private Node findMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            this.size--;
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            this.size--;
            return node.left;
        }
        node.right = removeMax(node.right);
        return node;
    }


    private Node search(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (node.key == key) {
            return node;
        } else if (node.key < key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }


    private static class Node {
        private int key;
        private int value;
        private Node left;
        private Node right;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Bst bst = new Bst();
        bst.insert(3, 3);
        bst.insert(4, 4);
        bst.insert(2, 2);
        bst.insert(5, 5);
        bst.insert(1, 1);

        bst.remove(3);

        bst.levelOrder();
    }

}
