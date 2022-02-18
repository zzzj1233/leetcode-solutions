package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-19 09:57
 */
public class Leet111 {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.buildTree("[3,9,20,null,null,15,7]");

        System.out.println(minDepth(treeNode));
    }

    public static int minDepth(TreeNode root) {
        return getDepth(root) ;
    }

    private static int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        int min = Integer.MAX_VALUE;

        if (node.left != null) {
            min = Math.min(getDepth(node.left), min);
        }

        if (node.right != null) {
            min = Math.min(getDepth(node.right), min);
        }

        return min + 1;
    }

}
