package com.zzzj.tree;

/**
 * @author Zzzj
 * @create 2022-08-13 22:29
 */
public class Leet701 {


    public static TreeNode insertIntoBST(TreeNode root, int val) {
        return dfs(root, val);
    }

    public static TreeNode dfs(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val > root.val) {
            root.right = dfs(root.right, val);
        } else {
            root.left = dfs(root.left, val);
        }
        return root;
    }

}
