package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-15 16:05
 */
public class Leet1080 {

    public static void main(String[] args) {
        System.out.println(sufficientSubset(TreeNode.buildTree("[1,2,-3,-5,null,4,null]"), -1));
    }

    public static TreeNode sufficientSubset(TreeNode root, int limit) {
        return dfs(root, limit, 0);
    }


    public static TreeNode dfs(TreeNode root, int limit, int pathSum) {
        if (root == null) {
            return null;
        }

        TreeNode left = dfs(root.left, limit, pathSum + root.val);
        TreeNode right = dfs(root.right, limit, pathSum + root.val);

        if (left == null && right == null) {
            if (pathSum + root.val < limit) {
                return null;
            }
            if (root.left != null || root.right != null) {
                return null;
            }
        }

        root.left = left;
        root.right = right;

        return root;
    }

}
