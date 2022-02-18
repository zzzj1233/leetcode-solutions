package com.zzzj.tree;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zzzj
 * @create 2021-11-11 16:07
 */
public class Leet116 {

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

        public static Node buildNode(String src) {
            src = src.substring(1, src.length() - 1);
            String[] strList = src.split(",");

            Node root;
            Node result = null;
            Queue<Node> queue = new LinkedList<>();
            for (int i = 0; i < strList.length; i++) {
                if (i == 0) {
                    root = new Node(Integer.parseInt(strList[i]));
                    result = root;
                    queue.add(root);
                }
                if (!queue.isEmpty()) {
                    root = queue.poll();
                } else {
                    break;
                }
                if (i + 1 < strList.length && !strList[i + 1].equals("null")) {
                    root.left = new Node(Integer.parseInt(strList[i + 1]));
                    queue.add(root.left);
                }
                if (i + 2 < strList.length && !strList[i + 2].equals("null")) {
                    root.right = new Node(Integer.parseInt(strList[i + 2]));
                    queue.add(root.right);
                }
                i = i + 1;
            }
            return result;
        }

    }


    public static void main(String[] args) {
        final Node node1 = Node.buildNode("[-1,0,1,2,3,4,5,6,7,8,9,10,11,12,13]");
        final Node node2 = connect(node1);

        final Node dfsNode = Node.buildNode("[-1,0,1,2,3,4,5,6,7,8,9,10,11,12,13]");
        dfs(dfsNode);
        System.out.println("~");
    }

    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        return root;
    }

    private static void dfs(Node root) {
        // prefect tree
        if (root.left == null) {
            return;
        }
        dfs(root.left);
        dfs(root.right);
        conn(root.left, root.right);
    }

    private static void conn(Node left, Node right) {
        if (left == null) {
            return;
        }
        left.next = right;
        conn(left.right, right.left);
    }

    private static void bfs(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            List<Node> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node first = queue.removeFirst();
                first.next = queue.peekFirst();
                if (first.left != null) {
                    list.add(first.left);
                }
                if (first.right != null) {
                    list.add(first.right);
                }
            }
            queue.addAll(list);
        }
    }

}
