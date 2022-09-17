package com.zzzj.link;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-30 11:36
 */
public class Leet2433 {

    public static TreeNode convertBiNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftest = leftest(root);
        inOrder(root);
        return leftest;
    }

    public static TreeNode leftest(TreeNode root) {
        if (root.left == null) {
            return root;
        }
        return leftest(root.left);
    }

    static TreeNode preNode;

    public static void inOrder(TreeNode root) {

        if (root.left != null) {
            inOrder(root.left);
            root.left = null;
        }

        if (preNode != null) {
            preNode.right = root;
        }
        preNode = root;

        if (root.right != null) {
            inOrder(root.right);
        }

    }
}
