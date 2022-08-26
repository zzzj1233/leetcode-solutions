package com.zzzj.tree;


import java.util.HashSet;

/**
 * @author zzzj
 * @create 2022-08-08 10:41
 */
public class Leet1650 {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public static Node lowestCommonAncestor(Node p, Node q) {

        Node a = p;
        Node b = q;

        while (a != b) {
            a = a == null ? p : a.parent;
            b = b == null ? q : b.parent;
        }

        return a;
    }

    public static Node lowestCommonAncestor2(Node p, Node q) {
        Node parent = p;

        HashSet<Integer> set = new HashSet<>();

        while (parent != null) {
            set.add(parent.val);
            parent = parent.parent;
        }

        parent = q;

        while (parent != null) {
            if (set.contains(parent.val)) {
                return parent;
            }
            parent = parent.parent;
        }

        return null;
    }

}
