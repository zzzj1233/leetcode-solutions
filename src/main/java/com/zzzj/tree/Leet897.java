package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-09 14:24
 */
public class Leet897 {

    public static void main(String[] args) {

    }

    private static TreeNode answer;

    public static TreeNode increasingBST(TreeNode root) {
        answer = new TreeNode();
        TreeNode dummy = answer;
        postOrder(root);
        return dummy.right;
    }


    private static void postOrder(TreeNode root) {
        if (root.left != null) {
            postOrder(root.left);
        }
        answer.right = new TreeNode(root.val);
        answer = answer.right;
        if (root.right != null) {
            postOrder(root.right);
        }
    }

}
