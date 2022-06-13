package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-09 12:25
 */
public class Leet1214 {

    public static boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        return dfs1(root1, root2, target);
    }

    public static boolean dfs1(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null) {
            return false;
        }
        if (dfs(root2, target - root1.val)) {
            return true;
        }
        return dfs1(root1.left, root2, target) || dfs1(root1.right, root2, target);
    }

    public static boolean dfs(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.val == target) {
            return true;
        }
        if (root.val > target) {
            return dfs(root.left, target);
        }
        return dfs(root.right, target);
    }

}
