package com.zzzj.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-08 10:20
 */
public class Leet1490 {


    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public static Node cloneTree(Node root) {
        if (root == null) {
            return null;
        }
        Node cloneRoot = new Node(root.val);

        dfs(cloneRoot, root);

        return cloneRoot;
    }

    public static void dfs(Node cloneRoot, Node root) {
        List<Node> children = new ArrayList<>(root.children.size());

        for (Node child : root.children) {
            Node node = new Node(child.val);
            dfs(node, child);
            children.add(node);
        }

        cloneRoot.children = children;
    }

}
