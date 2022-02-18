package com.zzzj.link;

import com.zzzj.leet.TreeNode;
import com.zzzj.uf.UfTest;

/**
 * @author zzzj
 * @create 2021-11-26 15:21
 */
public class Leet114 {

    public static void main(String[] args) {
        flatten(TreeNode.buildTree("[1,2,5,3,4,null,6]"));
    }

    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        preOrder(root);
    }

    private static TreeNode preOrder(TreeNode root) {
        TreeNode right = root.right;

        TreeNode left = root.left;

        root.left = null;

        root.right = left;

        TreeNode retNode = root;

        if (left != null) {
            retNode = preOrder(left);
        }

        retNode.right = right;

        if (right != null) {
            retNode = preOrder(right);
        }


        return retNode;
    }


}
