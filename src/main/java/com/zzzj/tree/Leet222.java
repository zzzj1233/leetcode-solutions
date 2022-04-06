package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-12 11:52
 */
public class Leet222 {

    public static void main(String[] args) {
        // [1,2,3,4,5,6,7]
        TreeNode root = TreeNode.buildTree("[1,2,3,4,5,6]");
        System.out.println(countNodes(root));
    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = 1 + leftHeight(root);
        int rightHeight = 1 + rightHeight(root);

        if (leftHeight == rightHeight) {
            return (int) (Math.pow(2, leftHeight) - 1);
        }

        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    public static int rightHeight(TreeNode root) {
        if (root.right != null) {
            return rightHeight(root.right) + 1;
        }
        return 0;
    }

    public static int leftHeight(TreeNode root) {
        if (root.left != null) {
            return leftHeight(root.left) + 1;
        }
        return 0;
    }

}
