package com.zzzj.tree;

/**
 * @author Zzzj
 * @create 2022-08-13 16:11
 */
public class Leet669 {


    public static TreeNode trimBST(TreeNode root, int low, int high) {
        return dfs(root, low, high);
    }

    public static TreeNode dfs(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return dfs(root.right, low, high);
        }
        if (root.val > high) {
            return dfs(root.left, low, high);
        }

        root.left = dfs(root.left, low, high);
        root.right = dfs(root.right, low, high);


        return root;
    }


}
