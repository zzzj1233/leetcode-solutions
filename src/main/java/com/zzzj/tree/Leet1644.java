package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-10 11:54
 */
public class Leet1644 {


    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = dfs(root, p, q);
        return canFind(root, p) && canFind(root, q) ? ans : null;
    }

    public static boolean canFind(TreeNode root, TreeNode p) {
        if (root == null) {
            return false;
        }
        if (root == p) {
            return true;
        }
        return canFind(root.left, p) || canFind(root.right, p);
    }

    public static TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }

        TreeNode left = dfs(root.left, p, q);
        TreeNode right = dfs(root.right, p, q);

        if (left == null && right == null) {
            return null;
        }

        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        return root;
    }

}
