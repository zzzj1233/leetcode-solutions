package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-12 11:52
 */
public class Leet222 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree("[1,2,3,4]");
        System.out.println(countNodes(root));
    }

    public static int countNodes(TreeNode root) {
        return dfs(root);
    }

    // 如果左树比右树高 那么右树一定是一颗完全二叉树
    // 如果左树和右树一样高 那么左树一定是一颗完全二叉树
    private static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftLevel = getLevel(root.left);
        int rightLevel = getLevel(root.right);

        if (leftLevel == rightLevel) {
            return 1 + ((int) Math.pow(2, leftLevel) - 1) + dfs(root.right);
        }

        return 1 + ((int) Math.pow(2, rightLevel) - 1) + dfs(root.left);
    }

    private static int getLevel(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getLevel(root.left), getLevel(root.right));
    }

}
