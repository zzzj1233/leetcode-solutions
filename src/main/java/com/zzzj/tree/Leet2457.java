package com.zzzj.tree;

/**
 * @author Zzzj
 * @create 2022-08-13 19:26
 */
public class Leet2457 {

    public static void main(String[] args) {
        System.out.println(isSubStructure(TreeNode.buildTree("[1,2,3,4]"), TreeNode.buildTree("[3]")));
    }


    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        }
        return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
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
