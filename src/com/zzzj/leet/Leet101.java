package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-19 11:43
 */
public class Leet101 {

    public static void main(String[] args) {
        System.out.println(isSymmetric(TreeNode.buildTree("[1,2,2,null,3,null,3]")));
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        return isSymmetric(root.left, root.right);
    }

    private static boolean isSymmetric(TreeNode left, TreeNode right) {
        // leftNode.left = rightNode.right
        // leftNode.right = rightNode.left

        if (left == null && right != null) {
            return false;
        }

        if (right == null && left != null) {
            return false;
        }

        if (left != null && left.val != right.val) {
            return false;
        }

        if (left != null) {
            return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
        }

        return true;
    }

}