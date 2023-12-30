package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-08 16:16
 */
public class Leet543 {

    public static void main(String[] args) {

        System.out.println(diameterOfBinaryTree(TreeNode.buildTree("[1,2,3,4,5]")));

    }


    static int ans;

    public static int diameterOfBinaryTree(TreeNode root) {

        ans = Integer.MIN_VALUE;

        dfs(root);

        return ans;
    }

    private static int dfs(TreeNode root) {
        if (root == null)
            return 0;
        int lv = dfs(root.left);
        int rv = dfs(root.right);
        ans = Math.max(ans, lv + rv);
        return Math.max(
                lv + 1,
                rv + 1
        );
    }

}
