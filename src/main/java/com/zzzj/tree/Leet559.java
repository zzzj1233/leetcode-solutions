package com.zzzj.tree;

import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-22 16:41
 */
public class Leet559 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        if (root.children != null) {
            for (Node child : root.children) {
                max = Math.max(max, maxDepth(child));
            }
        }
        return max + 1;
    }

}
