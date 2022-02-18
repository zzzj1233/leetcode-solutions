package com.zzzj.tree;

import com.zzzj.leet.LeetUtils;
import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-12 11:10
 */
public class Leet114 {

    public static void main(String[] args) {
        final String random = LeetUtils.randomTreeStr(10);
        System.out.println(random);
        final TreeNode node = TreeNode.buildTree(random);
        flatten(node);
        System.out.println(node.serialize());
    }

    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root);
    }

    private static TreeNode dfs(TreeNode root) {
        TreeNode left = root.left;
        TreeNode right = root.right;


        if (left != null) {
            root.right = root.left;
            root.left = null;
            TreeNode leftRet = dfs(left);
            if (right != null) {
                leftRet.right = right;
                return dfs(right);
            }
            return leftRet;
        }

        if (right != null) {
            TreeNode rightRet = dfs(right);
            return rightRet;
        }


        return root;
    }


}
