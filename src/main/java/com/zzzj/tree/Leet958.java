package com.zzzj.tree;

import com.zzzj.leet.LeetUtils;
import com.zzzj.leet.TreeNode;


/**
 * @author zzzj
 * @create 2021-11-08 10:31
 */
public class Leet958 {

    public static void main(String[] args) {
        System.out.println(isCompleteTree(TreeNode.buildTree("[80,50,4,4,1,1,23,79,null,null]")));
        for (int i = 0; i < 10; i++) {
            String str = LeetUtils.randomTreeStr(10);
            TreeNode treeNode = TreeNode.buildTree(str);
            System.out.println(str);
            System.out.println(dfs(treeNode));
            System.out.println("======================");
        }
        System.out.println("finish");
    }

    public static boolean isCompleteTree(TreeNode root) {
        return dfs(root);
    }

    private static boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    private static class Info {
        public boolean hasLeft;
        public boolean hasRight;
        public boolean isBalanced;
        public boolean isLeaf;
        public int maxHeight;
        public int minHeight;
    }

    private static boolean dfs(TreeNode root) {
        return getInfo(root).isBalanced;
    }

    private static Info getInfo(TreeNode root) {
        if (root == null) {
            Info info = new Info();
            info.isBalanced = true;
            info.isLeaf = true;
            return info;
        }

        Info info = new Info();
        info.hasLeft = root.left != null;
        info.hasRight = root.right != null;
        info.isBalanced = true;
        info.isLeaf = root.left == null && root.right == null;

        Info leftInfo = getInfo(root.left);
        Info rightInfo = getInfo(root.right);

        info.maxHeight = 1 + Math.max(leftInfo.maxHeight, rightInfo.maxHeight);
        info.minHeight = 1 + Math.min(leftInfo.minHeight, rightInfo.minHeight);

        if (!leftInfo.isBalanced || !rightInfo.isBalanced) {
            info.isBalanced = false;
        }

        if (root.left == null && root.right != null) {
            info.isBalanced = false;
        }

        if (!leftInfo.hasRight && rightInfo.hasLeft) {
            info.isBalanced = false;
        }

        if (!leftInfo.isLeaf && rightInfo.isLeaf) {
            info.isBalanced = false;
        }

        if (!rightInfo.isLeaf && leftInfo.isLeaf) {
            info.isBalanced = false;
        }

        if (leftInfo.maxHeight - rightInfo.minHeight > 1 || rightInfo.maxHeight - leftInfo.minHeight > 1) {
            info.isBalanced = false;
        }

        return info;
    }


}
