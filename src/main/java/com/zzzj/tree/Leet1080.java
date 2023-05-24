package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-15 16:05
 */
public class Leet1080 {

    public static void main(String[] args) {
        // [1,2,-3,-5,null,4,null]
        // -1
        System.out.println(sufficientSubset(TreeNode.buildTree("[1,2,-3,-5,null,4,null]"), -1));
    }

    public static TreeNode sufficientSubset(TreeNode root, int limit) {
        return dfs(root, limit, 0);
    }

    public static TreeNode dfs(TreeNode root, int limit, int sum) {
        if (root == null) {
            return null;
        }

        root.left = dfs(root.left, limit, sum + root.val);
        root.right = dfs(root.right, limit, sum + root.val);

        if (root.left == null && root.right == null) {
            return sum + root.val >= limit ? root : null;
        }

        return root;
    }

}
