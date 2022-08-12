package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-12 18:54
 */
public class Leet1448 {

    public static int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    public static int dfs(TreeNode root, int max) {
        if (root == null) {
            return 0;
        }

        return (max > root.val ? 0 : 1) + dfs(root.left, Math.max(max, root.val)) + dfs(root.right, Math.max(max, root.val));
    }

}
