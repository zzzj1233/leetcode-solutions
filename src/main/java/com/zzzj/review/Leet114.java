package com.zzzj.review;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2023-03-16 17:35
 */
public class Leet114 {

    public static void main(String[] args) {
        TreeNode node = TreeNode.buildTree("[1,2,null,3,4,5]");

        flatten(node);

        System.out.println(node.serialize());
    }

    public static void flatten(TreeNode root) {
        f(root);
    }

    public static TreeNode f(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 1. root.left = root.right
        // 2. root.right = originLeft.right

        TreeNode left = root.left;

        TreeNode right = root.right;

        TreeNode leftRes = f(left);

        TreeNode rightRes = f(right);

        if (left != null) {
            root.right = left;
        }

        if (leftRes != null && right != null) {
            leftRes.right = right;
        }

        root.left = null;

        return rightRes == null ? root : rightRes;
    }

}
