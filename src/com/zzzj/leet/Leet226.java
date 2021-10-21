package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-19 11:08
 */
public class Leet226 {

    public static void main(String[] args) {

    }

    public TreeNode invertTree(TreeNode root) {
        return invert(root);
    }

    private TreeNode invert(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode left = node.left;
        TreeNode right = node.right;

        node.left = invert(right);
        node.right = invert(left);
        return node;
    }

}
