package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-20 10:32
 */
public class Leet404 {

    private static int sum = 0;

    public static void main(String[] args) {
        sumOfLeftLeaves(TreeNode.buildTree("[-9,-3,2,null,4,4,0,-6,null,-5]"));
        System.out.println(sum);
    }

    private static void sum(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }

        sum(root.left);
        sum(root.right);
    }

    public static int sumOfLeftLeaves(TreeNode root) {
        sum = 0;
        sum(root);
        return sum;
    }

}
