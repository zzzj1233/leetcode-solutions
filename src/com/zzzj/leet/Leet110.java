package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-19 17:00
 */
public class Leet110 {

    private static boolean isBalanced = true;

    public static void main(String[] args) {
        System.out.println(isBalanced(TreeNode.buildTree("[1]")));
    }

    private static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left) + 1;

        int rightHeight = getHeight(root.right) + 1;

        if (Math.abs(leftHeight - rightHeight) > 1){
            isBalanced = false;
        }

        return Math.max(leftHeight, rightHeight);
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }

        isBalanced = true;
        getHeight(root);
        return isBalanced;
    }

}