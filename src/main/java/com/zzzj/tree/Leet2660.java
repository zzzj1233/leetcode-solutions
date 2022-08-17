package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-15 10:14
 */
public class Leet2660 {
    public static void main(String[] args) {
        System.out.println(isValidBST(TreeNode.buildTree("[2,1,3]")));
    }

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean dfs(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }

        if (root.left != null && root.left.val >= root.val) {
            return false;
        }

        if (root.right != null && root.right.val <= root.val) {
            return false;
        }

        if (root.val <= min || root.val >= max) {
            return false;
        }

        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }

}
