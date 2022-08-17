package com.zzzj.tree;

/**
 * @author Zzzj
 * @create 2022-08-13 22:23
 */
public class Leet2659 {

    public static boolean checkSubTree(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        }
        if (A == null) {
            return false;
        }
        return dfs(A, B) || checkSubTree(A.left, B) || checkSubTree(A.right, B);
    }

    public static boolean dfs(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        if (A.val != B.val) {
            return false;
        }
        return dfs(A.left, B.left) && dfs(A.right, B.right);
    }
}
