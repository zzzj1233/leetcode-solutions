package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-16 12:19
 */
public class Leet1325 {

    public static TreeNode removeLeafNodes(TreeNode root, int target) {
        return postOrder(root, target);
    }

    public static TreeNode postOrder(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        root.left = postOrder(root.left, target);
        root.right = postOrder(root.right, target);

        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }

        return root;
    }

}
