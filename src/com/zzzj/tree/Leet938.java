package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-10 15:37
 */
public class Leet938 {

    public static void main(String[] args) {
        System.out.println(rangeSumBST(TreeNode.buildTree("[10,5,15,3,7,null,18]"), 7, 15));
    }

    public static int rangeSumBST(TreeNode root, int low, int high) {
        return dfs(root, low, high);
    }

    private static int dfs(TreeNode root, int low, int high) {
        if (root.val < low) {
            return root.right == null ? 0 : dfs(root.right, low, high);
        }
        if (root.val > high) {
            return root.left == null ? 0 : dfs(root.left, low, high);
        }
        int val = root.val;
        if (root.left != null) {
            val += dfs(root.left, low, high);
        }
        if (root.right != null) {
            val += dfs(root.right, low, high);
        }
        return val;
    }

}
