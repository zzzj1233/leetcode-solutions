package com.zzzj.leet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-08-05 22:46
 */
public class Leet1506 {

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


    public static Node findRoot(List<Node> tree) {
        int xor = 0;

        for (Node node : tree) {
            for (Node child : node.children) {
                xor ^= child.val;
            }
        }

        for (Node node : tree) {
            xor ^= node.val;
        }

        for (Node node : tree) {
            if (node.val == xor) {
                return node;
            }
        }

        return null;
    }

}
