package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2023-01-05 18:12
 */
public class Leet156 {

    public static TreeNode upsideDownBinaryTree(TreeNode root) {

        // 1. 原来的左子节点变成新的根节点
        // 2. 原来的根节点变成新的右子节点
        // 3. 原来的右子节点变成新的左子节点

        if (root == null) {
            return null;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        root.right = null;

        TreeNode leftResult = upsideDownBinaryTree(left);

        if (leftResult == null) {
            return root;
        }

        left.right = root;
        left.left = right;

        return leftResult;
    }

}
