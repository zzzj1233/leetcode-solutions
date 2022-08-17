package com.zzzj.tree;

/**
 * @author Zzzj
 * @create 2022-08-13 16:50
 */
public class Leet1123 {

    public static void main(String[] args) {
        System.out.println(lcaDeepestLeaves(TreeNode.buildTree("[3,5,1,6,2,0,8,null,null,7,4,null,null,null,null,9,null,null,13]")));
    }

    public static TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root);
    }

    public static TreeNode dfs(TreeNode root) {
        int left = depth(root.left);
        int right = depth(root.right);

        if (left == right) {
            return root;
        }
        if (left > right) {
            return dfs(root.left);
        }
        return dfs(root.right);
    }

    public static int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }


}
