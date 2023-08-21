package com.zzzj.dp;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2023-08-21 15:14
 */
public class Leet337 {

    public static void main(String[] args) {

        System.out.println(rob(TreeNode.buildTree("[3,2,3,null,3,null,1]")));

        System.out.println(rob(TreeNode.buildTree("[3,4,5,1,3,null,1]")));

        System.out.println(rob(TreeNode.buildTree("[4,1,null,2,null,3]")));

    }

    public static int rob(TreeNode root) {
        int[] dfs = dfs(root);
        return Math.max(dfs[0], dfs[1]);
    }

    public static int[] dfs(TreeNode root) {

        if (root == null) return new int[2];

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int[] res = new int[2];

        res[0] = Math.max(left[1], left[0]) + Math.max(right[1], right[0]);
        res[1] = root.val + left[0] + right[0];

        return res;
    }
}
