package com.zzzj.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-19 16:30
 */
public class Leet133 {

    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }


    static Node[] bucket;

    public static Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        bucket = new Node[101];

        return dfs(node);
    }

    public static Node dfs(Node origin) {
        Node root = new Node(origin.val);

        bucket[root.val] = root;

        List<Node> nodes = new ArrayList<>(origin.neighbors.size());

        for (int i = 0; i < origin.neighbors.size(); i++) {
            nodes.add(null);
        }

        root.neighbors = nodes;

        for (int i = 0; i < origin.neighbors.size(); i++) {
            Node neighbor = origin.neighbors.get(i);
            if (bucket[neighbor.val] == null) {
                bucket[neighbor.val] = dfs(neighbor);
            }
            nodes.set(i, bucket[neighbor.val]);
        }
        return root;
    }

}
