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

        root.left = dfs(root.left);
        root.right = dfs(root.right);

        if (root.val == 0){
            return root.left == null && root.right == null ? null : root;
        }

        return root;
    }

}
