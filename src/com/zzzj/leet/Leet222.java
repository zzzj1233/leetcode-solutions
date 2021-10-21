package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-19 12:05
 */
public class Leet222 {

    private static int count(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);

        if (leftDepth == rightDepth) {
            return (int) (count(root.right) + Math.pow(2, leftDepth));
        } else {
            return (int) (count(root.left) + Math.pow(2, rightDepth));
        }
    }

    public static int countNodes(TreeNode root) {
        return count(root);
    }


    private static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }


    public static void main(String[] args) {
        TreeNode tree = TreeNode.buildTree("[1,2,3,4,5,6]");

        System.out.println(countNodes(tree));
    }

}
