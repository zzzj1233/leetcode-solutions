package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-20 14:53
 */
public class Leet129 {

    public static void main(String[] args) {
        System.out.println(sumNumbers(TreeNode.buildTree("[1,2,3]")));
    }

    public static int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, 0);
    }

    public static int dfs(TreeNode root, int pathSum) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return pathSum * 10 + root.val;
        }
        return dfs(root.left, pathSum * 10 + root.val) + dfs(root.right, pathSum * 10 + root.val);
    }

}
