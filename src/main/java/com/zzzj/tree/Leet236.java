package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-16 11:29
 */
public class Leet236 {

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        return dfs(root, p, q);
    }

    public static TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return null;
        }

        if (node.val == q.val || node.val == p.val) {
            return node;
        }

        // 后序遍历
        TreeNode left = dfs(node.left, p, q);

        TreeNode right = dfs(node.right, p, q);

        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        return node;
    }

}
