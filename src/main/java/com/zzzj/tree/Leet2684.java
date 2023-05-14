package com.zzzj.tree;

public class Leet2684 {

    public static TreeNode expandBinaryTree(TreeNode root) {
        dfs(root);
        return root;
    }

    public static void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        if (left != null) {
            root.left = new TreeNode(-1);
            root.left.left = left;
            dfs(left);
        }

        if (right != null) {
            root.right = new TreeNode(-1);
            root.right.right = right;
            dfs(right);
        }

    }

}
