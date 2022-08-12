package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-12 18:49
 */
public class Leet814 {

    public static TreeNode pruneTree(TreeNode root) {
        return dfs(root);
    }

    public static TreeNode dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root.val == 0 ? root : null;
        }
        TreeNode leftNode = dfs(root.left);
        TreeNode rightNode = dfs(root.right);

        if (leftNode == null && rightNode == null && root.val == 0){
            return null;
        }

        return root;
    }

}
