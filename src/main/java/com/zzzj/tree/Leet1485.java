package com.zzzj.tree;

import java.util.HashMap;
import java.util.Map;

public class Leet1485 {

    public static class Node {
        int val;
        Node left;
        Node right;
        Node random;

        Node() {
        }

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right, Node random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }

    public class NodeCopy extends Node {
        public NodeCopy(int val) {
            super(val);
        }
    }

    public NodeCopy copyRandomBinaryTree(Node root) {
        if (root == null) {
            return null;
        }

        Map<Node, NodeCopy> map = new HashMap<>();

        dfs(root, map);
        dfs2(root, map);

        return map.get(root);
    }

    public void dfs2(Node root, Map<Node, NodeCopy> map) {
        if (root.left != null) {
            dfs2(root.left, map);
        }
        if (root.right != null) {
            dfs2(root.right, map);
        }
        NodeCopy copy = map.get(root);
        copy.left = map.get(root.left);
        copy.right = map.get(root.right);
        copy.random = map.get(root.random);
    }

    public void dfs(Node root, Map<Node, NodeCopy> map) {
        if (root.left != null) {
            dfs(root.left, map);
        }
        if (root.right != null) {
            dfs(root.right, map);
        }

        NodeCopy copy = new NodeCopy(root.val);
        map.put(root, copy);
    }
}
