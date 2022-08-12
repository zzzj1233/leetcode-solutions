package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-11 18:34
 */
public class Leet510 {

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }


    public static Node inorderSuccessor(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        while (node.parent != null) {
            if (node == node.parent.left) {
                return node.parent;
            }
            node = node.parent;
        }

        return null;
    }

}
