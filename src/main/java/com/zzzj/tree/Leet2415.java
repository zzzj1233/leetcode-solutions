package com.zzzj.tree;

public class Leet2415 {


    public static void main(String[] args) {
        System.out.println(reverseOddLevels(TreeNode.buildTree("[2,1,3,4,7,11,29,18]")).serialize());
    }

    public static TreeNode reverseOddLevels(TreeNode root) {

        if (root == null) {
            return null;
        }

        dfs(root.left, root.right, 1);

        return root;
    }

    public static void dfs(TreeNode left, TreeNode right, int level) {
        if (left == null) {
            return;
        }

        if (level % 2 != 0) {
            int temp = left.val;
            left.val = right.val;
            right.val = temp;
        }
        dfs(left.left, right.right, level + 1);
        dfs(left.right, right.left, level + 1);
    }

}
